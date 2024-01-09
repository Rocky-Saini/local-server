package com.digital.signage.service

import com.digital.signage.dto.UserActivityModulesDTO
import com.digital.signage.models.Response
import org.springframework.stereotype.Service
import java.io.IOException

/**
 * @author -Ravi Kumar created on 1/21/2023 9:47 PM
 * @project - Digital Sign-edge
 */
@Service
interface UserActivityService {

    val userActivityModulesDtos: List<UserActivityModulesDTO>

    val modulesForUserActivityReports: Response<List<UserActivityModulesDTO>>

}