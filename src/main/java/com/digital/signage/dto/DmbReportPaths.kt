package com.digital.signage.dto

import java.nio.file.Path

data class DmbReportPaths(
    val xlsReportPath: Path,
    val pdfReportPath: Path
)