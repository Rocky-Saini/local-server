package digital.signage.util

import com.digital.signage.models.Logs
import com.digital.signage.util.DeviceOs
import com.digital.signage.util.readableDateInIST

fun readableLogFileName(log: Logs) =
        (if (log.logFileStartTime != null && log.logFileEndTime != null) {
            "${readableDateInIST(log.logFileStartTime)} to ${readableDateInIST(
                    log.logFileEndTime)}"
        } else {
            log.logFileName
        })!!

fun getDesiredLogFileName(log: Logs, deviceName: String, deviceOs: DeviceOs): String =
        "$deviceName ${readableLogFileName(log).replace(":", "-")}.${deviceOs.logFileExtension}"