package com.digital.signage.dto.report;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.util.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.digital.signage.annotations.ReportConstants.*;


@Data
@Getter
@Setter
@PdfTitle(title = DEVICE_EXPIRY_LICENSE_REPORT_TITLE)
public class DeviceReportDto {
    @ReportColumn(order = 1, columnName = MEDIA_PLAYER_NAME)
    @PdfColumn(order = 1, columnName = MEDIA_PLAYER_NAME)
    private String MediaPlayerName;
    @ReportColumn(order = 2, columnName = DEVICE_LOCATION)
    @PdfColumn(order = 2, columnName = DEVICE_LOCATION)
    private String location;
    @ReportColumn(order = 3, columnName = LICENSE_CODE)
    @PdfColumn(order = 3, columnName = LICENSE_CODE)
    private String licenceCode;
    @ReportColumn(order = 4, columnName = DEVICE_LICENSE_START_DATE)
    @PdfColumn(order = 4, columnName = DEVICE_LICENSE_START_DATE)
    @ReportSimpleDateFormat
    private Date licenceStartDate;
    @ReportColumn(order = 5, columnName = DEVICE_LICENSE_END_DATE)
    @PdfColumn(order = 5, columnName = DEVICE_LICENSE_END_DATE)
    @ReportSimpleDateFormat
    private Date licenceEndDate;
    @ReportColumn(order = 6, columnName = LICENSE_STATUS)
    @PdfColumn(order = 6, columnName = LICENSE_STATUS)
    private Status status;



}