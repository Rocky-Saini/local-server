package com.digital.signage.service.impl

import com.digital.signage.dto.UserActivityModulesDTO
import com.digital.signage.models.UserActivity
import com.digital.signage.models.Response
import com.digital.signage.service.UserActivityService
import com.digital.signage.util.Message
import com.digital.signage.util.UserActivityEnum
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.math.BigInteger
import java.util.*
import javax.persistence.EntityManager
import javax.servlet.ServletContext

/**
 * @author -Ravi Kumar created on 1/21/2023 9:50 PM
 * @project - Digital Sign-edge
 */
@Service
class UserActivityServiceImpl : UserActivityService {

    @Autowired
    lateinit var servletContext: ServletContext

    @Autowired
    lateinit var responseMessage: Message

    @Autowired
    lateinit var entityManager: EntityManager

    override val userActivityModulesDtos: List<UserActivityModulesDTO>
        get() = userActivityReportsDTOList

    override val modulesForUserActivityReports: Response<List<UserActivityModulesDTO>>
        get() {
            logger.debug(
                "Calling ...getModulesForUserActivityReports() method inside" + logger.javaClass
                    .simpleName
            )
            val successResponse = Response<List<UserActivityModulesDTO>>()
            successResponse.result = userActivityReportsDTOList
            return successResponse
        }

    private fun sortKey(key: String): String {
        return if (key == "date") {
            "activityTime"
        } else {
            key
        }
    }

    private fun getQueryResult(
        customerId: Long?,
        searchField: String,
        currentPage: Int?,
        numPerPage: Int?,
        queryMap: Map<String, Any>?
    ): Pair<List<UserActivity>, BigInteger> {

        val selectStr = "SELECT DISTINCT * "
        var joinsQueryString = " FROM  useractivity WHERE "

        if (customerId != null) {
            joinsQueryString = "$joinsQueryString customerId = $customerId AND "
        }

        var queryString = selectStr + joinsQueryString

        if (!StringUtils.isEmpty(searchField)) {
            queryString += searchField
        }

        val sqlQuery = entityManager.createNativeQuery(queryString, UserActivity::class.java)
        if (queryMap != null && queryMap.isNotEmpty()) {
            for ((key, value) in queryMap) {
                sqlQuery.setParameter(key, value)
            }
        }
        sqlQuery.firstResult = (currentPage!! - 1) * numPerPage!!
        sqlQuery.maxResults = numPerPage
        val userActivityList = sqlQuery.resultList as MutableList<UserActivity>

        val queryStringCount = "SELECT COUNT(1) as counter $joinsQueryString$searchField"

        val sqlQueryCount = entityManager.createNativeQuery(queryStringCount)
        if (queryMap != null && queryMap.isNotEmpty()) {
            for ((key, value) in queryMap) {
                sqlQueryCount.setParameter(key, value)
            }
        }

        val count = sqlQueryCount.singleResult as BigInteger

        return Pair(userActivityList, count)
    }

    companion object {

        val logger = LoggerFactory.getLogger(UserActivityServiceImpl::class.java)

        lateinit var userActivityReportsDTOList: List<UserActivityModulesDTO>

        init {
            val myList = ArrayList<UserActivityModulesDTO>()
            for (userActType in UserActivityEnum.ModulesEnum.values()) {
                val userActivityReportsDTO = UserActivityModulesDTO()
                userActivityReportsDTO.key = userActType
                userActivityReportsDTO.name = userActType.value
                myList.add(userActivityReportsDTO)
                userActivityReportsDTOList = Collections.unmodifiableList(myList)
            }
        }
    }
}
