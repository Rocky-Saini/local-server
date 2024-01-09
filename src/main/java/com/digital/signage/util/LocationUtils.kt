package com.digital.signage.util

import com.digital.signage.dto.LatLng
import com.digital.signage.dto.LatLngDMS
import com.digital.signage.dto.LocationApiDTO
import com.digital.signage.dto.LocationIdDeviceCountDTO
import com.digital.signage.exceptions.CouldNotParseLocationErrorException
import com.digital.signage.models.Location
import com.digital.signage.models.LocationFetchingErrors
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.floor

/**
 * @author -Ravi Kumar created on 1/23/2023 12:22 AM
 * @project - Digital Sign-edge
 */
private val logger: Logger = LoggerFactory.getLogger(object {}::class.java.`package`.name)

fun recursivelySetDeviceCount(
    locationApiDTO: LocationApiDTO,
    level: Int,
    locationIdToLevelMap: MutableMap<Long, Int>
) {
    if (!locationApiDTO.childLocations.isNullOrEmpty()) {

        val isLevelMatched = level == locationIdToLevelMap[locationApiDTO.locationId]
        logger.debug("isLevelMatched = $isLevelMatched")
        var count = 0L
        // iterate on all children
        for (location in locationApiDTO.childLocations) {
            logger.debug("in for loop location = ${location.locationName}")
            //  count if the level matches
            if (isLevelMatched) { // level is matched
                // do the sum
                val dc: Long? = if (location == null) {
                    null
                } else {
                    (location as LocationApiDTO).deviceCount
                }
                count = if (dc == null) {
                    count
                } else {
                    count + dc
                }
            } else {
                // if level is matched then we don't have to go at children level
                logger.debug("making a recursive call")
                recursivelySetDeviceCount(location as LocationApiDTO, level, locationIdToLevelMap)
            }
        }

        // set the count
        locationApiDTO.deviceCount = count
    }
}

fun convertToMap(
    locationIdDeviceCountDTOS: List<LocationIdDeviceCountDTO>
): MutableMap<Long, Long?> =
    locationIdDeviceCountDTOS.map {
        it.location!!.locationId to it.deviceCount
    }.toMap().toMutableMap()

fun latLngToDms(latLng: LatLng): LatLngDMS {
    return LatLngDMS(
        getDms(latLng.latitude) + if (latLng.latitude >= 0.0) " N" else " S",
        getDms(latLng.longitude) + if (latLng.longitude >= 0.0) " E" else " W"
    )
}

fun getDms(latOrLng: Double): String {
    val absLatOrLng = abs(latOrLng)
    val deg = floor(absLatOrLng).toInt()
    val min = floor((absLatOrLng - deg) * 60).toInt()
    val sec = BigDecimal((((absLatOrLng - deg) * 60) - min) * 60).setScale(
        4,
        RoundingMode.HALF_DOWN
    )
    return "" + deg + "º " + min + "' " + sec + '"'
}

fun getLocationIdToLocationMap(locations: List<Location>): Map<Long, Location> =
    locations.convertListToMap(java.util.function.Function { it.locationId })

fun getLocationParentIdToLocationMap(
    locations: List<Location>
): Map<Long?, Location> =
    locations.convertListToMap(java.util.function.Function { 0L})//it.parentId })

fun getCommaSeparatedLocationString(
    concatenatedLocationString: String,
    location: Location,
    locationIdToLocationMap: Map<Long?, Location>
): String {
    return if (0L == null) {//location.parentId
        // root location
        "$concatenatedLocationString, ${location.locationName}"
    } else {
        getCommaSeparatedLocationString(
            if (concatenatedLocationString.isEmpty()) {
                location.locationName
            } else {
                "$concatenatedLocationString, ${location.locationName}"
            },
            locationIdToLocationMap[0L]!!, //location.parentId
            locationIdToLocationMap
        )
    }
}

@Throws(CouldNotParseLocationErrorException::class)
fun getLocationFetchingErrorsAsList(
    locationFetchingErrors: String?
): List<LocationFetchingErrors>? {
    return if (locationFetchingErrors.isNullOrEmpty()) {
        null
    } else locationFetchingErrors.split(",")
        .map {
            try {
                LocationFetchingErrors.valueOf(it.trim())
            } catch (e: Exception) {
                throw CouldNotParseLocationErrorException()
            }
        }.toList()
}

@Throws(CouldNotParseLocationErrorException::class)
fun validateLocationFetchingErrors(
    locationFetchingErrors: String?
) {
    if (!locationFetchingErrors.isNullOrEmpty()) {
        locationFetchingErrors.split(",")
            .forEach {
                try {
                    LocationFetchingErrors.valueOf(it.trim())
                } catch (e: Exception) {
                    throw CouldNotParseLocationErrorException()
                }
            }
    }
}