package com.digital.signage.dto

data class DmbReportDataAndFiles(
    val dbmReportPaths: DmbReportPaths,
    val dmbReportResponseDataList: List<DmbReportResponseData>
)