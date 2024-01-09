package com.digital.signage.service.impl

import com.digital.signage.localserver.apiservice.AllExceptionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AllExceptionServiceImpl : AllExceptionService {
    override fun saveException(t: Throwable, requestApi: String) {
        logger.debug("saveException : $requestApi : $t")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AllExceptionServiceImpl::class.java)
    }
}