package com.digital.signage.service

import com.digital.signage.models.Response
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import javax.servlet.http.HttpServletRequest

interface SnapshotImprovedService {

    @Throws(IOException::class)
    fun uploadSnapShot(
        file: MultipartFile,
        str: String,
        request: HttpServletRequest
    ): Response<out Any>
}