//package com.digital.signage.localserver.filter
//
//import com.digital.signage.localserver.config.LocalServerData
//import com.digital.signage.localserver.service.LocalServerDataService
//import com.digital.signage.util.ApplicationConstants
//import com.digital.signage.util.SecurityConstants.TOKEN_PREFIX
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.Ordered
//import org.springframework.core.annotation.Order
//import javax.servlet.*
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//@Configuration
//@Order(Ordered.LOWEST_PRECEDENCE)
//class LocalDataFilter(
//    val localServerDataService: LocalServerDataService,
//    val localServerData: LocalServerData
//) : Filter {
//
//    private val logger: Logger = LoggerFactory.getLogger(LocalDataFilter::class.java)
//
//    override fun init(filterConfig: FilterConfig?) {
//        logger.info("########## Initiating LocalDataFilter filter ##########")
//    }
//
//    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?,
//                          filterChain: FilterChain?) {
//        val request = servletRequest as HttpServletRequest
//        val response = servletResponse as HttpServletResponse
//        val auth = request.getHeader(ApplicationConstants.Headers.AUTHORIZATION)
//
//        if (auth != null && auth.trim().startsWith(TOKEN_PREFIX)) {
//            var s = String(auth.toCharArray())
//            s = s.replace(TOKEN_PREFIX, "")
//            if (!localServerData.isDataComplete()) {
//                // async call
//                localServerDataService.downloadConfigForLocalServer(s)
//            }
//        }
//        filterChain!!.doFilter(request, response)
//    }
//
//    override fun destroy() {
//        logger.info("########## Destroying LocalDataFilter filter ##########")
//    }
//
//}