package com.digital.signage.models;

import com.digital.signage.util.UserActivityEnum;

import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:04 PM
 * @project - Digital Sign-edge
 */
public class UserActivityBuilder {
    private Long userActivityId;
    private Long userId;
    private String userName;
    private Long customerId;
    private Date date;
    private String time;
    private UserActivityEnum.ModulesEnum module;
    private String moduleDisplayName;
    private String action;
    private Long roleId;
    private String roleName;
    private String comments;
    private Long entityId;
    private String entityName;
    private String internalIP;
    private String externalIP;
    private Date activityTime;

    public UserActivityBuilder() {
        this.activityTime = new Date();
    }

    public UserActivityBuilder(Long userActivityId, Long userId, String userName, Long customerId,
                               Date date, String time, UserActivityEnum.ModulesEnum module, String moduleDisplayName,
                               String action, Long roleId, String roleName, String comments, Long entityId,
                               String entityName, String internalIP, String externalIP) {
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

    public UserActivityBuilder setUserActivityId(Long userActivityId) {
        this.userActivityId = userActivityId;
        return this;
    }

    public UserActivityBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserActivityBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserActivityBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public UserActivityBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public UserActivityBuilder setModule(UserActivityEnum.ModulesEnum module) {
        this.module = module;
        return this;
    }

    public UserActivityBuilder setModuleDisplayName(String moduleDisplayName) {
        this.moduleDisplayName = moduleDisplayName;
        return this;
    }

    public UserActivityBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public UserActivityBuilder setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public UserActivityBuilder setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public UserActivityBuilder setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public UserActivityBuilder setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public UserActivityBuilder setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public UserActivityBuilder setInternalIP(String internalIP) {
        this.internalIP = internalIP;
        return this;
    }

    public UserActivityBuilder setExternalIP(String externalIP) {
        this.externalIP = externalIP;
        return this;
    }

    public UserActivityBuilder setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public UserActivity build() {
        return new UserActivity(userActivityId, userId, userName, customerId, date, time,
                module, moduleDisplayName, action, roleId, roleName,
                comments, entityId, entityName, internalIP,
                externalIP);
    }
}
