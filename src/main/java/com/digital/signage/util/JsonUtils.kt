package digital.signage.util

import com.digital.signage.dto.TOrResponse
import com.digital.signage.models.Response
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

/**
 * 1. if json parsing is success then pair will have the response DTO
 *
 * 2. if the json JsonParseException or JsonMappingException if found
 * then send a 400 bad request.
 *
 * 3. If IOException is found then send a 500 error.
 */
fun <T> jsonFromString(
        objectMapper: ObjectMapper,
        json: String,
        classOfT: Class<T>
): TOrResponse<T> {
    return try {
        val jsonDTO = objectMapper.readValue<T>(json, classOfT)
        TOrResponse(jsonDTO, null)
    } catch (e: JsonParseException) {
        TOrResponse(null, Response.createBadRequestResponseFromException(e))
    } catch (e: JsonMappingException) {
        TOrResponse(null, Response.createBadRequestResponseFromException(e))
    } catch (e: IOException) {
        TOrResponse(null, Response.createInternalServerErrorResponseFromException(e))
    }
}