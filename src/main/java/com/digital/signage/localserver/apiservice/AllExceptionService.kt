package com.digital.signage.localserver.apiservice

import org.springframework.stereotype.Service

@Service
interface AllExceptionService {

    fun saveException(t: Throwable, requestApi: String)

}