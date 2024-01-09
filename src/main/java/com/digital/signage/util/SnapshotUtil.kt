package com.digital.signage.util;

import com.digital.signage.models.DeviceSnapshot
import com.digital.signage.models.SnapShot
import com.digital.signage.util.ApplicationConstants.THUMBNAIL_EXTENSION


fun getFilenameForSnapshot(snapShot: SnapShot, deviceName: String): String =
        (if (snapShot.snapshotType == null) "" else "${snapShot.snapshotType} ") +
                deviceName +
                (if (snapShot.snapshotTimeInDB == null) "" else " ${readableDateInISTWithSeconds(
                        snapShot.snapshotTimeInDB)}")

fun getFilenameCurrentSnapshot(
        isThumbnail: Boolean,
        deviceSnapshot: DeviceSnapshot
): String = "CURRENT " + readableDateInISTWithSeconds(deviceSnapshot.snapshotTime) +
        if (isThumbnail) {
            ".$THUMBNAIL_EXTENSION"
        } else {
            ".${deviceSnapshot.fileExtension}"
        }