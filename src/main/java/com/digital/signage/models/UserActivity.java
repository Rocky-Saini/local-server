package com.digital.signage.models;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.PdfTitle;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.annotations.ReportSimpleDateFormat;
import com.digital.signage.util.ReportConstants;
import com.digital.signage.util.UserActivityEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/21/2023 9:42 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "user_activity")
@PdfTitle(title = ReportConstants.USER_Activity_REPORT_TITLE)
public class UserActivity {

    public static final String USERID = "user_id";
    public static final String ROLEID = "role_id";
    public static final String DATE = "date";
    public static final String COMMENTS = "comments";
    public static final String MODULE = "module";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_activity_id")
    private Long userActivityId;

    @Column(name = USERID)
    private Long userId;

    @Column(name = "user_name")
    @ReportColumn(columnName = "User Name", order = 1)
    @PdfColumn(columnName = "User Name", order = 1)
    private String userName;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = DATE)
    @ReportSimpleDateFormat
    @ReportColumn(columnName = "Date", order = 2)
    @PdfColumn(columnName = "Date", order = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
    @JsonProperty(value = DATE)
    private Date date;

    @Column(name = "time")
    @ReportColumn(columnName = "Time", order = 3)
    @PdfColumn(columnName = "Time", order = 3)
    private String time;

    @Enumerated(EnumType.STRING)
    @Column(name = MODULE)
    @ReportColumn(columnName = "Module", order = 4)
    @PdfColumn(columnName = "Module", order = 4)
    private UserActivityEnum.ModulesEnum module;

    @Column(name = "module_display_name")
    private String moduleDisplayName;

    @Column(name = "action")
    @ReportColumn(columnName = "Action", order = 5)
    @PdfColumn(columnName = "Action", order = 5)
    private String action;

    @Column(name = ROLEID)
    private Long roleId;

    @Column(name = "role_name")
    @ReportColumn(columnName = "Role", order = 6)
    @PdfColumn(columnName = "Role", order = 6)
    private String roleName;

    @Column(name = COMMENTS)
    @ReportColumn(columnName = "Comments", order = 7)
    @PdfColumn(columnName = "Comments", order = 7)
    private String comments;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "entity_name")
    @ReportColumn(columnName = "Name Of Entity", order = 8)
    @PdfColumn(columnName = "Name Of Entity", order = 8)
    private String entityName;

    @Column(name = "internal_ip")
    @ReportColumn(columnName = "Internal IP", order = 9)
    @PdfColumn(columnName = "Internal IP", order = 9)
    private String internalIP;

    @Column(name = "external_ip")
    @ReportColumn(columnName = "External IP", order = 10)
    @PdfColumn(columnName = "External IP", order = 10)
    private String externalIP;

    @JsonIgnore
    @Column(name = "activity_time")
    private Date activityTime;

    public UserActivity() {
        this.activityTime = new Date();
    }

    public UserActivity(Long userActivityId, Long userId, String userName, Long customerId, Date date,
                        String time, UserActivityEnum.ModulesEnum module, String moduleDisplayName, String action,
                        Long roleId, String roleName, String comments, Long entityId, String entityName,
                        String internalIP,
                        String externalIP) {
        this.userActivityId = userActivityId;
        this.userId = userId;
        this.userName = userName;
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.module = module;
        this.moduleDisplayName = moduleDisplayName;
        this.action = action;
        this.roleId = roleId;
        this.roleName = roleName;
        this.comments = comments;
        this.entityId = entityId;
        this.entityName = entityName;
        this.internalIP = internalIP;
        this.externalIP = externalIP;
        this.activityTime = new Date();
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Long getUserActivityId() {
        return userActivityId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public UserActivityEnum.ModulesEnum getModule() {
        return module;
    }

    public String getModuleDisplayName() {
        return moduleDisplayName;
    }

    public String getAction() {
        return action;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getComments() {
        return comments;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getInternalIP() {
        return internalIP;
    }

    public String getExternalIP() {
        return externalIP;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
