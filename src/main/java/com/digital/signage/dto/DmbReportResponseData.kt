package com.digital.signage.dto

import com.digital.signage.annotations.PdfColumn
import com.digital.signage.annotations.ReportColumn
import com.digital.signage.util.TypeEnum

data class DmbReportResponseData(
    @ReportColumn(columnName = "Type", order = 2)
    @PdfColumn(columnName = "Type", order = 2)
    val type: TypeEnum? = null,

    @ReportColumn(columnName = "No of Hours", order = 3)
    @PdfColumn(columnName = "No of Hours", order = 3)
    val numberOfHrs: Int? = null,

    @ReportColumn(columnName = "Count", order = 4)
    @PdfColumn(columnName = "Count", order = 4)
    val count: Int? = null,

    @ReportColumn(columnName = "Double", order = 5)
    @PdfColumn(columnName = "Double", order = 5)
    val percentage: Double? = null,

    val devices: List<DeviceIdAndName> = emptyList()
)