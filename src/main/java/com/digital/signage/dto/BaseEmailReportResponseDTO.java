package com.digital.signage.dto;

public class BaseEmailReportResponseDTO<T> extends BaseReportResponseDto<T> {
    public static final BaseEmailReportResponseDTO<?> EMPTY = new BaseEmailReportResponseDTO<>(true);

    public BaseEmailReportResponseDTO() {
        this(false);
    }

    private BaseEmailReportResponseDTO(boolean immutableData) {
        super(immutableData);
    }

    private boolean isReportSentOnEmail;

    public boolean getIsReportSentOnEmail() {
        return isReportSentOnEmail;
    }

    public void setIsReportSentOnEmail(boolean reportSentOnEmail) {
        isReportSentOnEmail = reportSentOnEmail;
    }
}

