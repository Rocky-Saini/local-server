package com.digital.signage.models;

import com.digital.signage.util.ApplicationConstants.INSTALLABLE_FILES_BUILD_OS_EXTENSIONS
import com.digital.signage.util.BuildOs
import java.nio.file.Paths
import javax.persistence.*

@Entity(name = LatestBuildVersionsByBuildOs.TABLE_NAME)
data class LatestBuildVersionsByBuildOs(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_ID)
    var id: Long? = null,

    @Column(name = COL_VERSION)
    var version: String? = null,

    @Column(name = COL_BUILD_OS)
    var buildOs: BuildOs? = null,

    @Column(name = COL_ON_PREMISES_ID)
    var onPremisesId: Long? = null,

    @Column(name = COL_BUILD_FILENAME_OR_S3_OBJECT_KEY)
    var buildFileNameOrS3ObjectKey: String? = null,

    @Column(name = COL_BUILD_MD5_CHECKSUM)
    var buildMd5Checksum: String? = null
) {

    fun getFilename(isS3Enabled: Boolean): String? {
        if (buildFileNameOrS3ObjectKey == null) {
            return null
        }
        if (isS3Enabled) {
            //buildFileNameOrS3ObjectKey!!.split("/").last()
            return buildOs!!.name + version + "." + INSTALLABLE_FILES_BUILD_OS_EXTENSIONS[buildOs]
        }
        return Paths.get(buildFileNameOrS3ObjectKey!!).toFile().name
    }

    companion object {
        const val TABLE_NAME: String = "latestBuildVersionsByBuildOs"
        const val COL_ID: String = "id"
        const val COL_VERSION: String = "version"
        const val COL_BUILD_OS: String = "buildOs"
        const val COL_ON_PREMISES_ID: String = "onPremisesId"
        const val COL_BUILD_FILENAME_OR_S3_OBJECT_KEY: String = "buildFilenameOrS3ObjectKey"
        const val COL_BUILD_MD5_CHECKSUM = "buildMd5Checksum"
    }
}