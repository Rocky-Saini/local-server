package com.digital.signage.report.S3;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ReportHelper {
    /*    public List<Long> getNewAddLicenseIds(Optional<List<UserActivity>> userActivityList) {
            List<Long> newLicenseIds = new ArrayList<>(userActivityList.map(userActivities -> userActivities.stream()
                            .filter(ua -> ua.getAction()
                            .equals(UserActivityEnum.ActionEnum.ADDED.getValue())).map(UserActivity::getEntityId).toList())
                    .orElse(List.of()));
            List<Long> renewLicenseIds = userActivityList.map(userActivities -> userActivities.stream().filter(ua -> ua.getAction()
                            .equals(UserActivityEnum.ActionEnum.RENEW.getValue())).map(UserActivity::getEntityId).toList())
                    .orElse(List.of());
            if(newLicenseIds.isEmpty() || renewLicenseIds.isEmpty()) return newLicenseIds;
            newLicenseIds.removeAll(removeDuplicates(renewLicenseIds));
            return newLicenseIds;
        }
        public List<UserActivity> getGracedLicense(Optional<List<UserActivity>> userActivityList) {
            return userActivityList.map(userActivities -> userActivities.stream().filter(ua -> ua.getAction()
                    .equals(UserActivityEnum.ActionEnum.ADDED_GRACE.getValue())).toList()).orElse(List.of());
        }
        public List<Long> getGracedLicenseIds(List<UserActivity> userActivityList) {
            Set<Long> graceLicenseIds = userActivityList.stream().map(UserActivity::getEntityId).collect(Collectors.toSet());
            return new ArrayList<>(graceLicenseIds);
        }*/
/*    public List<Long> getRenewedLicenseId(Optional<List<UserActivity>> userActivityList) {
        List<Long> graceIds = getGracedLicenseIds(userActivityList);
        if(graceIds != null && !graceIds.isEmpty()) {
            return userActivityList.map(userActivities -> userActivities.stream()
                    .filter(ua -> ua.getAction()
                            .equals(UserActivityEnum.ActionEnum.RENEW.getValue()) && !graceIds.contains(ua.getEntityId()))
                    .map(UserActivity::getEntityId).toList())
                    .orElse(List.of());
        }
        return List.of();
    }*/
    private List<Long> removeDuplicates(List<Long> ids) {
        Set<Long> uniqueSet = new HashSet<>();
        List<Long> uniqueList = new ArrayList<>();
        if(!ids.isEmpty()) {
            for (Long id : ids) {
                if (uniqueSet.add(id)) {
                    uniqueList.add(id);
                } else {
                    uniqueList.remove(id);
                }
            }
        }
        return uniqueList;
    }
}
