package com.digital.signage.service.impl

import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.models.FieldMessage
import com.digital.signage.models.Response
import com.digital.signage.models.ValidationErrorResponse
import com.digital.signage.service.BaseService
import com.digital.signage.util.Message
import com.digital.signage.util.PushModeForAngular
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import java.io.IOException
import java.util.ArrayList
import javax.servlet.http.HttpServletResponse

/**
 * @author -Ravi Kumar created on 1/22/2023 7:29 PM
 * @project - Digital Sign-edge
 */
abstract class BaseServiceWithServerLaunchConfigImpl : BaseService {
    @Autowired
    protected lateinit var message: Message

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var serverLaunchConfig: ServerLaunchConfig

    protected fun isS3Enabled(): Boolean = serverLaunchConfig.isS3Enabled!!

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<out Any> {
        val l = ex.bindingResult.allErrors
        var errorDetails: MutableList<FieldMessage>?
        var validationErrorResponse: ValidationErrorResponse? = null
        if (l.size > 0) {
            errorDetails = ArrayList()
            var errorDetail: FieldMessage?
            for (error in l) {
                errorDetail = FieldMessage(
                    (error as FieldError).field,
                    //message.get(error.getDefaultMessage()));
                    error.getDefaultMessage()
                )
                errorDetails.add(errorDetail)
            }
            validationErrorResponse = ValidationErrorResponse()
            validationErrorResponse.result = errorDetails
        }
        return ResponseEntity<ValidationErrorResponse>(
            validationErrorResponse,
            HttpStatus.BAD_REQUEST
        )
    }

    @Throws(IOException::class)
    protected fun writeResponseToHttpServletResponse(
        httpServletResponse: HttpServletResponse,
        response: Response<*>
    ) {
        httpServletResponse.status = if (response.httpStatusCode == null) 200 else response.httpStatusCode
        httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        httpServletResponse.writer.write(objectMapper.writeValueAsString(response))
        httpServletResponse.writer.flush()
        httpServletResponse.writer.close()
    }

    fun isWebPushViaFirebase(): Boolean {
        return PushModeForAngular.FIREBASE == serverLaunchConfig.pushModeForAngular
    }

}