package com.digital.signage.service.impl

import com.digital.signage.constants.UrlPaths
import com.digital.signage.service.DefaultThumbnailService
import com.digital.signage.util.ApplicationConstants.ROOT_CONTENT_DEFAULT_THUMBNAIL_DIRECTORY
import com.digital.signage.util.removeSlashFromEndOfUrlIfRequired
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import java.nio.file.Path
import java.nio.file.Paths
@Service
class DefaultThumbnailServiceSharedDriveImpl : BaseServiceWithServerLaunchConfigImpl(),
        DefaultThumbnailService {

    /*@Value("\${root.storage.dir}")
    private lateinit var rootStorageDir: String*/

    private fun getFilePath(thumbnailType: DefaultThumbnailService.ThumbnailType) = Paths.get(
            getDefaultThumbnailDir().toString(), thumbnailType.fileName)

    private fun getDefaultThumbnailDir() = Paths.get("/var/opt/panasonic",
            ROOT_CONTENT_DEFAULT_THUMBNAIL_DIRECTORY)

    /*override fun uploadDefaultThumbnail(inputFilePath: Path,
            thumbnailType: DefaultThumbnailService.ThumbnailType, deleteInputFile: Boolean) {
        uploadDefaultThumbnail(FileInputStream(inputFilePath.toFile()), thumbnailType)
        if (deleteInputFile) Files.delete(inputFilePath)
    }

    override fun uploadDefaultThumbnail(inputFileStream: InputStream,
            thumbnailType: DefaultThumbnailService.ThumbnailType) {
        inputFileStream.use {
            FileOutputStream(getFilePath(thumbnailType).toFile()).use { outputStream ->
                IOUtils.copy(it, outputStream)
            }
        }
    }*/

    override fun getDefaultThumbnailUrl(
            thumbnailType: DefaultThumbnailService.ThumbnailType) =
            "${removeSlashFromEndOfUrlIfRequired(
                    serverLaunchConfig.serverBaseUrlForAngularDownloadLinks!!)}${UrlPaths.PATH_DEFAULT_THUMBNAIL}/${thumbnailType.urlPath}"

    /*override fun downloadThumbnailFile(thumbnailType: DefaultThumbnailService.ThumbnailType,
            httpServletResponse: HttpServletResponse) {
        FileInputStream(getFilePath(thumbnailType).toFile()).use {
            httpServletResponse.outputStream.use { outputStream ->
                IOUtils.copy(it, outputStream)
            }
        }
    }

    override fun downloadThumbnailFile(thumbnailUrlPath: String?,
            httpServletResponse: HttpServletResponse) {
        val thumbnailType = DefaultThumbnailService.ThumbnailType.fromUrlPath(thumbnailUrlPath)
        if (thumbnailType == null) {
            writeResponseToHttpServletResponse(httpServletResponse,
                    NotFoundResponse<Any>("ThumbnailNotFound", "No such thumbnail"))
            return
        }
        downloadThumbnailFile(thumbnailType, httpServletResponse)
    }*/

}