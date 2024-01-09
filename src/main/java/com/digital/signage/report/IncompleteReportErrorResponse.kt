package com.digital.signage.report

import com.digital.signage.models.FieldMessage
import org.springframework.http.HttpStatus

class IncompleteReportErrorResponse(name: String, message: String) :
    com.digital.signage.models.Response<List<FieldMessage>>(null, null, name, HttpStatus.BAD_REQUEST.value(),
        message, HttpStatus.BAD_REQUEST.value()) {

    constructor(message: String) : this("reportNotCompleted", message)
}