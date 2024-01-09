package com.digital.signage.criteria;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:46 AM
 * @project - Digital Sign-edge
 */
public class Operator {

    private Operator() {
        //don't allow instantiate
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static String getOperatorSymbol(String operatorName) {
        switch (operatorName) {
            case "lt":
                return "<";
            case "gt":
                return ">";
            case "eq":
                return "=";
            case "lte":
                return "<=";
            case "gte":
                return ">=";
            case "lk":
                return "like";
            case "in":
                return "IN";
            default:
                return null;
        }
    }
}
