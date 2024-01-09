package com.digital.signage.models;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.ReportConstants;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/23/2023 5:33 PM
 * @project - Digital Sign-edge
 */
@Entity(name = Planogram.TABLE_NAME)
@PdfTitle(title = ReportConstants.PLANO_MODEL_TITLE)
public class Planogram implements EntityModel, Comparable<Planogram> {
    public static final String TABLE_NAME = "planogram";
    public static final String COL_PLANOGRAM_ID = "planogram_id";
    public static final String COL_STATUS = "status";

    public static final Map<String, ClassParam> paramTypeMap = new HashMap<>();

    // PlanogramStopButtonStates
    public static final String PLANOGRAM_STOP_BUTTON_STATE_HIDE = "HIDE";
    public static final String PLANOGRAM_STOP_BUTTON_STATE_SHOW = "SHOW";

    @JsonProperty("currentTime")
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long now;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COL_PLANOGRAM_ID)
    private Long planogramId;
    @JsonIgnore
    private Boolean isPriorityPlanogram;
    private Integer priority;

    @ReportColumn(order = 1, columnName = "Planogram Name")
    @PdfColumn(order = 1, columnName = "Planogram Name")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "planogram_id")
    private List<Logic> deviceLogic;

    private String planogramColor;

    @Transient
    @JsonProperty("canApprove")
    private Boolean canApprove;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
    @ReportSimpleDateFormat(value = ReportConstants.DATE_FORMAT_FOR_PDF_REPORT)
    @ReportColumn(order = 5, columnName = "Start Date")
    @PdfColumn(order = 5, columnName = "Start Date")
    private Date startDate;

    @ReportColumn(order = 7, columnName = "Start Time")
    @PdfColumn(order = 7, columnName = "Start Time")
    private String startTime;

    @ReportColumn(order = 8, columnName = "End Time")
    @PdfColumn(order = 8, columnName = "End Time")
    private String endTime;

    @Column(name = "current_level_of_approval")
    private byte currentLevelOfApproval;

    @JsonIgnore
    @Transient
    private Boolean isCurrentlyRunning;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
    @ReportSimpleDateFormat(value = ReportConstants.DATE_FORMAT_FOR_PDF_REPORT)
    @ReportColumn(order = 6, columnName = "End Date")
    @PdfColumn(order = 6, columnName = "End Date")
    private Date endDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "aspect_ratio_id")
    private AspectRatio aspectRatio;

    @Transient
    @JsonProperty("aspectRatioId")
    private Long aspectRatioId;

    @Column(name = COL_STATUS)
    private Status status;

    @Column(name = "created_by")
    private Long createdBy;

    @Transient
    @ReportColumn(order = 2, columnName = "Created By")
    @PdfColumn(order = 2, columnName = "Created By")
    private String createdByName;

    @ReportSimpleDateFormat(value = ReportConstants.VALID_DATE_TIME_PATTERN)
    @ReportColumn(order = 3, columnName = "Created On")
    @PdfColumn(order = 3, columnName = "Created On")
    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedOn;

    @Column(name = "customer_id")
    private Long customerId;

    @JsonIgnore
    @Column(name = "is_planogram_stopped")
    private Boolean isPlanogramStopped;

    @JsonIgnoreProperties(value = "planogramStopButtonAction")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String planogramStopButtonAction;


    static {
        paramTypeMap.put("from_date", new ClassParam(Date.class, null));
        paramTypeMap.put("to_date", new ClassParam(Date.class, null));
        paramTypeMap.put("from_time", new ClassParam(String.class, null));
        paramTypeMap.put("to_time", new ClassParam(String.class, null));
        paramTypeMap.put("duration_in_seconds", new ClassParam(Long.class, null));
        paramTypeMap.put("status", new ClassParam(Status.class, "status"));
        paramTypeMap.put("location", new ClassParam(Long.class, null));
        paramTypeMap.put("device_group_id", new ClassParam(Long.class, "deviceGroup.deviceGroupId"));
        paramTypeMap.put("device_id", new ClassParam(Long.class, "device.deviceId"));
        paramTypeMap.put("aspect_ratio", new ClassParam(Long.class, "planogram.aspectRatioId"));
    }

    public String getPlanogramColor() {
        return planogramColor;
    }

    @JsonIgnore
    public String getPlanogramColorForValidation() {
        return planogramColor;
    }

    @JsonProperty("priorityColor")
    public String getPriorityColor() {
        return ApplicationConstants.SUPER_PRIORITY_COLOR_CODE;
    }

    public List<Logic> getDeviceLogic() {
        return deviceLogic;
    }

    public void setDeviceLogic(List<Logic> deviceLogic) {
        this.deviceLogic = deviceLogic;
    }

    public void setPlanogramColor(String planogramColor) {
        this.planogramColor = planogramColor;
    }

    public byte getCurrentLevelOfApproval() {
        return currentLevelOfApproval;
    }

    public void setCurrentLevelOfApproval(byte currentLevelOfApproval) {
        this.currentLevelOfApproval = currentLevelOfApproval;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getAspectRatioId() {
        return aspectRatio != null ? aspectRatio.getAspectRatioId() : aspectRatioId;
    }

    public void setAspectRatioId(Long aspectRatioId) {
        this.aspectRatioId = aspectRatioId;
    }

    public AspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Boolean getCanApprove() {
        return canApprove;
    }

    public void setCanApprove(Boolean canApprove) {
        this.canApprove = canApprove;
    }

    @Override
    public int compareTo(Planogram in_obj) {
        return this.priority < in_obj.priority ? -1
                : this.priority > in_obj.priority ? 1
                : this.startDate.compareTo(in_obj.startDate);
    }

    public Long getPlanogramId() {
        return planogramId;
    }

    public void setPlanogramId(Long planogramId) {
        this.planogramId = planogramId;
    }

    @JsonProperty("isPriorityPlanogram")
    public Boolean getPriorityPlanogram() {
        return isPriorityPlanogram;
    }

    @JsonProperty("isPriorityPlanogram")
    public void setPriorityPlanogram(Boolean priorityPlanogram) {
        isPriorityPlanogram = priorityPlanogram;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Long getCustomerId() {
        return customerId;
    }

    @JsonProperty("isPastDated")
    public Boolean getPastDated() {
        if (endDate != null) {
            Date todayDate = DateUtils.floorDate(new Date());
            return todayDate.compareTo(endDate) > 0;
        }
        return null;
    }

    // https://pi-proj.atlassian.net/browse/SER-223
    @JsonIgnore
    public boolean isPlanogramSubmitable() {
        if (this.getStartDate() == null || this.getEndDate() == null
                || this.getStartTime() == null || this.getEndTime() == null) {
            return false;
        }

        List<Logic> logicList = this.getDeviceLogic();
        if (logicList == null || logicList.isEmpty()) {
            return false;
        }

        return true;
    }

    // END

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @JsonIgnore
    public LocalDate getLocalStartDate() {
        return DateUtils.getLocalDateFromDate(this.startDate);
    }

    @JsonIgnore
    public LocalDate getLocalEndDate() {
        return DateUtils.getLocalDateFromDate(this.endDate);
    }

    @JsonIgnore
    public LocalTime getLocalStartTime() {
        return DateUtils.getLocalTimeFromString(this.startTime);
    }

    @JsonIgnore
    public LocalTime getLocalEndTime() {
        return DateUtils.getLocalTimeFromString(this.endTime);
    }

    @Override
    public String toString() {
        return "Planogram{" +
                "planogramId=" + planogramId +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public void setIsPlanogramStopped(Boolean isPlanogramStopped) {
        this.isPlanogramStopped = isPlanogramStopped;
    }

    public Boolean getPlanogramStopped() {
        if (isPlanogramStopped == null) {
            return false;
        } else {
            return isPlanogramStopped;
        }
    }

    @JsonIgnore
    public Boolean getPlanogramStoppedAsFalse() {
        return !getPlanogramStopped();
    }

    @JsonProperty("isCurrentlyRunning")
    public Boolean getCurrentlyRunning() {
        return isCurrentlyRunning == null ? false : isCurrentlyRunning;
    }

    @JsonIgnore
    public void setCurrentlyRunning(Boolean currentlyRunning) {
        isCurrentlyRunning = currentlyRunning;
    }

    public void setPlanogramStopButtonAction(String planogramStopButtonAction) {
        this.planogramStopButtonAction = planogramStopButtonAction;
    }

    @JsonProperty(value = "planogramStopButtonAction")
    public String getPlanogramStopButtonAction() {
        return planogramStopButtonAction;
    }

    //@JsonProperty(value = "planogramStopButtonAction")
    //public String getPlanogramStopButtonAction() {
    //  if (isPlanogramStopped != null && isPlanogramStopped) {
    //    return PlanogramStopButtonState.HIDE;
    //  } else {
    //    if (state != null && state.equals(State.PUBLISHED)) {
    //      LocalDateTime planoEndDateTime =
    //          LocalDateTime.of(LocalDate.parse(DateTimeUtilsKt.getOnlyDateAsString(endDate)),
    //              getLocalEndTime());
    //      LocalDateTime currentDateTime = LocalDateTime.now();
    //      if (planoEndDateTime.isBefore(currentDateTime)) {
    //        return PlanogramStopButtonState.HIDE;
    //      } else {
    //        return PlanogramStopButtonState.SHOW;
    //      }
    //    } else {
    //      return PlanogramStopButtonState.HIDE;
    //    }
    //  }
    //}
}

