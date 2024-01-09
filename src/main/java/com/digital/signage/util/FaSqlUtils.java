package com.digital.signage.util;

public class FaSqlUtils {
    /*public static String campaignUpdateValidationSqlIfInUseInRuleAssociation(Long campaignId, Long customerId) {
        return "SELECT c.approverWorkFlow AS approverWorkFlow FROM " +
                "ruleAssociation AS ra INNER JOIN customer AS c ON c.customerId = ra.customerId " +
                "WHERE ra.campaignId = :campaignId GROUP BY ra.customerId ".trim();
    }*/

    public static String campaignUpdateValidationSqlIfInUseInRuleAssociation(Long campaignId) {
        return "SELECT c.approverWorkFlow AS approverWorkFlow FROM " +
                "ruleAssociation AS ra INNER JOIN customer AS c ON c.customerId = ra.customerId " +
                "WHERE ra.campaignId = :campaignId GROUP BY ra.customerId ".trim();
    }
}
