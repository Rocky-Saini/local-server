package com.digital.signage.components;

import com.digital.signage.dto.UserActivityModulesDTO;
import com.digital.signage.dto.UserIdFullNameRoleIdAndRoleName;
import com.digital.signage.models.UserActivity;
import com.digital.signage.models.User;
import com.digital.signage.models.UserActivityBuilder;
import com.digital.signage.repository.UserActivityRepository;
import com.digital.signage.service.UserActivityService;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.UserActivityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.digital.signage.util.DateUtils.getCurrentDateWithoutTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/21/2023 9:41 PM
 * @project - Digital Sign-edge
 */
@Component
public class UserActivityDataManager {

    private static final Logger logger = LoggerFactory.getLogger(UserActivityDataManager.class);
    private List<UserActivity> userActivityList = new ArrayList<>();

    @Autowired
    @Lazy
    private UserActivityService userActivityService;

    //@Autowired
    //private UserActivityRepository userActivityRepository;

    public static class SaveUserActivityBuilder {
        User user;
        Long customerId;
        UserActivityEnum.ModulesEnum module;
        Object action;
        Long entityId;
        String entityName;
        String internalIP;
        String externalIP;

        public static SaveUserActivityBuilder aBuilder() {
            return new SaveUserActivityBuilder();
        }

        public SaveUserActivityBuilder user(User user) {
            this.user = user;
            return this;
        }

        public SaveUserActivityBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public SaveUserActivityBuilder action(Object action) {
            this.action = action;
            return this;
        }

        public SaveUserActivityBuilder entityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public SaveUserActivityBuilder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public SaveUserActivityBuilder internalIP(String internalIP) {
            this.internalIP = internalIP;
            return this;
        }

        public SaveUserActivityBuilder externalIP(String externalIP) {
            this.externalIP = externalIP;
            return this;
        }

        public SaveUserActivityBuilder module(UserActivityEnum.ModulesEnum module) {
            this.module = module;
            return this;
        }
    }

    @Async
    public void saveUserActivity(SaveUserActivityBuilder saveUserActivityBuilder) {
        saveUserActivity(
                saveUserActivityBuilder.user,
                saveUserActivityBuilder.customerId,
                saveUserActivityBuilder.module,
                saveUserActivityBuilder.action,
                saveUserActivityBuilder.entityId,
                saveUserActivityBuilder.entityName,
                saveUserActivityBuilder.internalIP,
                saveUserActivityBuilder.externalIP
        );
    }

    @Async
    public void saveUserActivity(
            Long actorUser,
            Long customerId,
            UserActivityEnum.ModulesEnum module,
            Object action,
            Long entityId,
            String entityName,
            String internalIP,
            String externalIP
    ) {
        List<UserIdFullNameRoleIdAndRoleName> users = new ArrayList<>();
        //need to get data from user-service
        //userRepository.getUserIdFullNameRoleIdAndRoleNameByUserId(actorUser);
        if (users.size() == 1) {
            UserIdFullNameRoleIdAndRoleName user = users.get(0);
            saveUserActivity(
                    user.getUserId(),
                    user.getFullName(),
                    user.getRoleId(),
                    user.getRoleName(),
                    customerId,
                    module,
                    action,
                    entityId,
                    entityName,
                    internalIP,
                    externalIP
            );
        }
    }

    @Async
    public void saveUserActivity(
            Long userId,
            String userFullName,
            Long userRoleId,
            String userRoleName,
            Long customerId,
            UserActivityEnum.ModulesEnum module,
            Object action,
            Long entityId,
            String entityName,
            String internalIP,
            String externalIP
    ) {

        String moduleDisplayName = null;
        if (module != null) {
            moduleDisplayName = getModuleName(module);
        }

        String actionString = null;
        if (action instanceof UserActivityEnum.ActionEnum) {
            actionString = ((UserActivityEnum.ActionEnum) action).getValue();
        } else {
            actionString = action.toString();
        }
        UserActivity userActivity = new UserActivityBuilder()
                .setUserId(userId)
                .setUserName(userFullName)
                .setCustomerId(customerId)
                .setModule(module)
                .setModuleDisplayName(moduleDisplayName)
                .setDate(getCurrentDateWithoutTime())
                .setTime(DateUtils.convertTimestampIntoTime(DateUtils.getCurrentTime()).toString())
                .setAction(actionString)
                .setRoleId(userRoleId)
                .setRoleName(userRoleName)
                .setComments(getComments(entityName, actionString, entityId))
                .setEntityId(entityId)
                .setEntityName(entityName)
                .setInternalIP(internalIP)
                .setExternalIP(externalIP)
                .build();

        synchronized (UserActivityDataManager.class) {
            userActivityList.add(userActivity);
        }
    }

    @Async
    public void saveUserActivity(
            User user,
            Long customerId,
            UserActivityEnum.ModulesEnum module,
            Object action,
            Long entityId,
            String entityName,
            String internalIP,
            String externalIP
    ) {
        String roleName = null;
        Long roleId = null;
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            roleName = user.getRoles().iterator().next().getName();
        }
        if (user.getRoleIds() != null && !user.getRoleIds().isEmpty()) {
            roleId = user.getRoleIds().iterator().next();
        }
        saveUserActivity(
                user.getUserId(),
                user.getFullName(),
                roleId,
                roleName,
                customerId,
                module,
                action,
                entityId,
                entityName,
                internalIP,
                externalIP
        );
    }

    @Transactional
    public synchronized void writeToDb() {
        long start = System.currentTimeMillis();
        if (userActivityList == null || userActivityList.isEmpty()) {
            return;
        }
        // save data to DB
        //use webclient to save the data
        //userActivityRepository.saveAll(userActivityList);
        // clear in memory data
        userActivityList.clear();
        long operationTime = System.currentTimeMillis() - start;
    }

    private String getComments(String entityName, String action, Long entityId) {
        String comments = null;
        comments = entityName + " "
                + "" + action;//+ ":" + entityId; // commented out as per SER-891
        return comments;
    }

    private String getModuleName(UserActivityEnum.ModulesEnum module) {
        String moduleName = null;
        List<UserActivityModulesDTO> list = userActivityService.getUserActivityModulesDtos();
        if (list != null && !list.isEmpty()) {
            for (UserActivityModulesDTO userActivityModulesDTO : list) {
                if (userActivityModulesDTO.getKey().equals(module)) {
                    moduleName = userActivityModulesDTO.getName();
                    break;
                }
            }
        }
        return moduleName;
    }
}
