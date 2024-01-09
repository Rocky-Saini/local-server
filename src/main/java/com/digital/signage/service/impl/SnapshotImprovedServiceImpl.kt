package com.digital.signage.service.impl

import com.digital.signage.context.TenantContext
import com.digital.signage.dto.ListOfSnapshotsDTO
import com.digital.signage.models.NotFoundResponse
import com.digital.signage.models.Response
import com.digital.signage.models.SnapShot
import com.digital.signage.models.ValidationErrorResponse
import com.digital.signage.repository.SnapShotRepository
import com.digital.signage.service.CaptureLogsServiceFileInterface
import com.digital.signage.service.SnapshotConsumer
import com.digital.signage.service.SnapshotImprovedService
import com.digital.signage.service.SnapshotQueueService
import com.digital.signage.service.impl.CaptureLogServiceImpl.*
import com.digital.signage.util.ApplicationConstants.ROOT_SNAPSHOT_TEMP_DIRECTORY
import com.digital.signage.util.FileType
import com.digital.signage.util.Message
import com.digital.signage.util.ResponseCode
import com.digital.signage.util.ThumbnailsHelper
import digital.signage.util.jsonFromString
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Service
class SnapshotImprovedServiceImpl @Autowired constructor(
    private val snapshotQueueService: SnapshotQueueService,
    private val snapshotRepository: SnapShotRepository,
    private val thumbnailsHelper: ThumbnailsHelper,
    private val captureLogsServiceFileInterface: CaptureLogsServiceFileInterface
) : BaseServiceImpl(), SnapshotImprovedService {

    @Value("\${root.storage.dir}")
    private lateinit var rootStorageDir: String

    @PostConstruct
    fun onReady() {
        snapshotQueueService.registerConsumer(object : SnapshotConsumer {

            override fun processQueueId(queueId: String) {
                logger.debug("processing queueId = {}", queueId)
                val t1 = System.currentTimeMillis()
                var numberOfImages = 0
                var extractedPath: Path? = null
                try {
                    // read request dto from json
                    val listOfSnapshotsDTO = readRequestDtoFromJson(queueId)

                    val toBeSavedSnapshotsInDb = mutableListOf<SnapShot>()

                    // unzip / untar process thumbnail
                    extractedPath = unzipOrUntarAndProcessThumbnails(
                        listOfSnapshotsDTO,
                        toBeSavedSnapshotsInDb,
                        queueId
                    )

                    // save files to s3 or file system
                    captureLogsServiceFileInterface.uploadSnapshotToDesiredLocation(
                        extractedPath,
                        toBeSavedSnapshotsInDb,
                        listOfSnapshotsDTO.customerId!!,
                        listOfSnapshotsDTO.deviceId!!
                    )

                    // save to DB
                    saveToDb(toBeSavedSnapshotsInDb)
                    numberOfImages = listOfSnapshotsDTO.snapshots!!.size
                } catch (e: Exception) {
                    logger.error("Error processing snapshot", e)
                } finally {
                    // delete temp json
                    var path = getJsonFilePathByQueueId(queueId)
                    try {
                        logger.debug("deleting {}", path)
                        Files.deleteIfExists(path)
                    } catch (ioe: Exception) {
                        logger.error("Error deleting $path", ioe)
                    }

                    // delete temp zip / tar
                    try {
                        path = getSnapshotTarOrZipFilePathByQueueId(queueId)
                        logger.debug("deleting {}", path)
                        Files.deleteIfExists(path)
                    } catch (ioe: Exception) {
                        logger.error("Error deleting $path", ioe)
                    }

                    // delete extracted dir
                    try {
                        if (extractedPath != null) {
                            logger.debug("deleting {}", extractedPath)
                            FileUtils.deleteDirectory(extractedPath.toFile())
                        }
                    } catch (ioe: Exception) {
                        logger.error("Error deleting $extractedPath", ioe)
                    }
                    val t2 = System.currentTimeMillis()
                    logger.debug(
                        "Snapshot zip/tar processing for queueId = {}, {} images : = {}ms",
                        queueId,
                        numberOfImages,
                        (t2 - t1)
                    )
                }
            }
        })
    }

    private fun readRequestDtoFromJson(queueId: String): ListOfSnapshotsDTO =
        objectMapper.readValue(
            getJsonFilePathByQueueId(queueId).toFile(),
            ListOfSnapshotsDTO::class.java
        )

    fun createSnapshotToBeSavedAndAddToList(
        fileNameInTar: String,
        finalFilePathToBeSaved: Path,
        listOfSnapshotsDTO: ListOfSnapshotsDTO
    ): Optional<SnapShot> {
        for (snapShotRequestDTO in listOfSnapshotsDTO.snapshots!!) {
            if (snapShotRequestDTO.snapshotFileName.equals(
                    FilenameUtils.getName(fileNameInTar),
                    ignoreCase = true
                )
            ) {
                val toBeSavedSnapshot = SnapShot()
                toBeSavedSnapshot.deviceId = listOfSnapshotsDTO.deviceId
                toBeSavedSnapshot.snapshotTimeForJsonButTransient =
                    snapShotRequestDTO.snapshotTimeForJsonButTransient
                toBeSavedSnapshot.snapshotTimeInDB =
                    Date(snapShotRequestDTO.snapshotTimeForJsonButTransient)
                toBeSavedSnapshot.snapshotType = snapShotRequestDTO.snapshotType
                toBeSavedSnapshot.snapshotFileName = finalFilePathToBeSaved.fileName.toString()
                return Optional.of(toBeSavedSnapshot)
            }
        }
        return Optional.empty()
    }

    private fun generateThumbnail(sourceFile: Path, snapshot: SnapShot): Optional<Path> {
        return try {
            val thumbnailWithPath = thumbnailsHelper.getThumbnailFromFile(sourceFile.toString())
            snapshot.hasThumbnailGenerationFailed = false
            snapshot.thumbnailPath = thumbnailWithPath
            Optional.of(Paths.get(thumbnailWithPath))
        } catch (ioe: IOException) {
            logger.error("error", ioe)
            snapshot.hasThumbnailGenerationFailed = true
            Optional.empty()
        }
    }

    private fun processSnapshot(
        entryName: String,
        extractionDir: Path,
        inputStream: InputStream,
        listOfSnapshotsDTO: ListOfSnapshotsDTO,
        toBeSavedSnapshotsInDb: MutableList<SnapShot>
    ) {
        val toBeSavedFileName =
            UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(entryName)
        val toBeSavedFilePath =
            Paths.get(extractionDir.toString(), toBeSavedFileName)

        // extract image file
        toBeSavedFilePath.toFile().outputStream().use { fos ->
            IOUtils.copyLarge(inputStream, fos)
        }

        // create snapshot db object
        val optionalSnapshot = createSnapshotToBeSavedAndAddToList(
            entryName,
            toBeSavedFilePath,
            listOfSnapshotsDTO
        )
        if (!optionalSnapshot.isPresent) {
            return
        }

        val snapshot = optionalSnapshot.get()

        // generate thumbnail
        val optionalThumbnail = generateThumbnail(toBeSavedFilePath, snapshot)

        // add to list
        toBeSavedSnapshotsInDb.add(snapshot)
    }

    @Throws(IOException::class)
    private fun unzipOrUntarAndProcessThumbnails(
        listOfSnapshotsDTO: ListOfSnapshotsDTO,
        toBeSavedSnapshotsInDb: MutableList<SnapShot>,
        queueId: String
    ): Path {
        logger.debug("unzipOrUntarAndProcessThumbnails")
        val extractionDir = getSnapshotExtractionDir(queueId)
        extractionDir.toFile().mkdir()
        if (queueId.endsWith(".zip")) {
            extractionDir.toFile().mkdir()
            FileInputStream(getSnapshotTarOrZipFilePathByQueueId(queueId).toFile()).use { inputStream ->
                BufferedInputStream(inputStream).use { bufferedInputStream ->
                    ZipInputStream(bufferedInputStream).use { zis ->
                        var entry: ZipEntry? = null
                        while (zis.nextEntry.also { entry = it } != null) {
                            processSnapshot(
                                entry!!.name,
                                extractionDir,
                                zis,
                                listOfSnapshotsDTO,
                                toBeSavedSnapshotsInDb
                            )
                        }
                    }
                }
            }
        } else {
            FileInputStream(getSnapshotTarOrZipFilePathByQueueId(queueId).toFile()).use { inputStream ->
                BufferedInputStream(inputStream).use { bufferedInputStream ->
                    GzipCompressorInputStream(bufferedInputStream).use { gzipCompressorInputStream ->
                        TarArchiveInputStream(gzipCompressorInputStream).use { tis ->
                            var entry: TarArchiveEntry?
                            while (tis.nextTarEntry.also { entry = it } != null) {
                                processSnapshot(
                                    entry!!.name,
                                    extractionDir,
                                    tis,
                                    listOfSnapshotsDTO,
                                    toBeSavedSnapshotsInDb
                                )
                            }
                        }
                    }
                }
            }
        }
        return extractionDir
    }

    private fun saveToDb(toBeSavedSnapshotsInDb: List<SnapShot>) {
        logger.debug("saveToDb")
        snapshotRepository.saveAll(toBeSavedSnapshotsInDb)
    }

    private fun validateUploadSnapshot(
        file: MultipartFile,
        str: String,
        deviceId: Long
    ): Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>> {
        val (listOfSnapshotsDTO, response) = jsonFromString(
            objectMapper,
            str,
            ListOfSnapshotsDTO::class.java
        )
        if (response != null) {
            return Pair(Optional.of(response), Optional.empty())
        }
        logger.debug("lastOfSnapshotsDTO = $listOfSnapshotsDTO")

        Objects.requireNonNull(listOfSnapshotsDTO)
        // check if snapshot array is empty

        // check if snapshot array is empty
        if (listOfSnapshotsDTO?.snapshots == null || listOfSnapshotsDTO.snapshots!!.isEmpty()) {
            logger.error("validation failed in snapshots")
            return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                Optional.of(
                    Response.Builder.voidResponseBuilder()
                        .badRequest()
                        .code(ResponseCode.FAILURE)
                        .name("snapshots")
                        .message(message.get(Message.SnapShot.SNAPSHOTS_ARRAY_EMPTY))
                        .build()
                ),
                Optional.empty()
            )
        }

        listOfSnapshotsDTO.deviceId = deviceId
        Objects.requireNonNull(listOfSnapshotsDTO.snapshots)

        if (!fileSnapshotTimeValidation(listOfSnapshotsDTO)) {
            return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                Optional.of(
                    Response.Builder.voidResponseBuilder()
                        .badRequest()
                        .name("FileUploadValidationFail")
                        .message(message[Message.SnapShot.SNAPSHOT_FILE_SNAPSHOT_TIME_ERROR])
                        .build()
                ),
                Optional.empty()
            )
        }

        // check file formats
        if (!fileFormatCheckValidation(listOfSnapshotsDTO)) {
            logger.error("mismatched files in zip folder")
            return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                Optional.of(
                    Response.Builder.voidResponseBuilder()
                        .badRequest()
                        .name("FileUploadValidationFail")
                        .message(message[Message.SnapShot.SNAPSHOT_FILE_FORMAAT_ERROR])
                        .build()
                ),
                Optional.empty()
            )
        }

        val fileExt: String = getFileExtension(file)
        val fileType = if (fileExt.contains("tar") || fileExt.contains("gz")) {
            FileType.TAR
        } else if (fileExt.contains("zip")) {
            FileType.ZIP
        } else {
            null
        }

        if (fileType == null) {
            return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                Optional.of(
                    ValidationErrorResponse(
                        "InvalidFileType",
                        "Invalid file type $fileExt found. Supported file types  "
                    )
                ),
                Optional.empty()
            )
        }

        if (FileType.TAR == fileType) {
            // For tar.gz files
            if (!validateFilesWithTar(file, listOfSnapshotsDTO)) {
                logger.error("mismatch files in tar.gz")
                return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                    Optional.of(
                        NotFoundResponse<Void>(
                            "TarArchiveEntryMismatchError",
                            message[Message.SnapShot.SNAPSHOT_ZIP_ENTRY_MISMATCH_ERROR]
                        )
                    ),
                    Optional.empty()
                )
            }
        } else {
            // for zip files
            if (!validateFilesWithZip(file, listOfSnapshotsDTO)) {
                logger.error("mismatch files in zip")
                return Pair<Optional<Response<out Any>>, Optional<ListOfSnapshotsDTO>>(
                    Optional.of(
                        NotFoundResponse<Void>(
                            "ZipEntryMismatchError",
                            message[Message.SnapShot.SNAPSHOT_ZIP_ENTRY_MISMATCH_ERROR]
                        )
                    ),
                    Optional.empty()
                )
            }
            logger.debug("validateFilesWithZip method completed")
        }
        return Pair(
            Optional.empty(),
            Optional.of(listOfSnapshotsDTO)
        )
    }

    override fun uploadSnapShot(
        file: MultipartFile,
        str: String,
        request: HttpServletRequest
    ): Response<out Any> {
        val t1 = System.currentTimeMillis()
        val customerId = TenantContext.getCustomerId()
        val deviceId = TenantContext.getDeviceId()

        Objects.requireNonNull(customerId)
        Objects.requireNonNull(deviceId)

        val (optionalResponse, optionalListOfSnapshotsDTO) = validateUploadSnapshot(
            file,
            str,
            deviceId
        )
        if (optionalResponse.isPresent) {
            return optionalResponse.get()
        }

        if (!optionalListOfSnapshotsDTO.isPresent) {
            throw NullPointerException("listOfSnapshotsDTO is null")
        }
        val listOfSnapshotsDTO = optionalListOfSnapshotsDTO.get()
        listOfSnapshotsDTO.customerId = customerId

        val zipOrTarFileName = file.originalFilename!!
        val ext = FilenameUtils.getExtension(zipOrTarFileName)

        // save tar or zip file
        val queueId: String = "${UUID.randomUUID()}.$ext"
        logger.debug("queueId = {}", queueId)
        val pth = getSnapshotTarOrZipFilePathByQueueId(queueId)
        logger.debug("pth = {}, filePath = {}", pth, pth.toFile())
        file.transferTo(pth.toFile())

        // save json file
        objectMapper.writeValue(getJsonFilePathByQueueId(queueId).toFile(), listOfSnapshotsDTO)

        // add to queue
        snapshotQueueService.addToQueue(queueId)

        val t2 = System.currentTimeMillis()
        logger.debug("Snapshot POST API total time spent in service = ${t2 - t1}ms")

        return Response.Builder.voidResponseBuilder()
            .success()
            .name("FileUploadSuccessfully")
            .code(ResponseCode.SUCCESS)
            .message(message[Message.SnapShot.SNAPSHOT_FILE_UPLOADED_SUCCESS])
            .build()
    }

    private fun getSnapshotTarOrZipFilePathByQueueId(queueId: String): Path =
        Paths.get(rootStorageDir, ROOT_SNAPSHOT_TEMP_DIRECTORY, queueId)

    private fun getSnapshotExtractionDir(queueId: String): Path =
        Paths.get(rootStorageDir, ROOT_SNAPSHOT_TEMP_DIRECTORY, queueId.split(".")[0])

    private fun getJsonFilePathByQueueId(queueId: String): Path =
        Paths.get(rootStorageDir, ROOT_SNAPSHOT_TEMP_DIRECTORY, queueId.split(".")[0] + ".json")

    companion object {
        private val logger: Logger =
            LoggerFactory.getLogger(SnapshotImprovedServiceImpl::class.java)
    }


}