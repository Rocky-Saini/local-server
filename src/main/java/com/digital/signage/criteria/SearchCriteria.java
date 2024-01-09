package com.digital.signage.criteria;

import com.digital.signage.models.ClassParam;
import com.digital.signage.models.ValidationResult;
import com.digital.signage.util.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:45 AM
 * @project - Digital Sign-edge
 */
public class SearchCriteria {
    private SearchCriteria() {
    }

    public static String advanceSearchCriteria(
            Map<String, Map<String, Object>> searchQuery,
            Map<String, ClassParam> classParamMap,
            Map<String, Object> columnValueMap
    ) {

        StringBuilder stringQuery = new StringBuilder();
        Object objectValue = null;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            for (Map.Entry<String, Map<String, Object>> fieldMap : searchQuery.entrySet()) {
                ClassParam fieldNameClass = classParamMap.get(fieldMap.getKey());
                if (fieldNameClass != null) {
                    String fieldName = classParamMap.get(fieldMap.getKey()).getDbColumnName();
                    if (fieldName == null) {
                        continue; // ignore the columns which have null dbColumnName, as this will have some specific implementation
                    }
                    String fieldNameKey = classParamMap.get(fieldMap.getKey()).getDbColumnName();
                    Class dataClass = classParamMap.get(fieldMap.getKey()).getType();
                    Map<String, Object> value = fieldMap.getValue();
                    for (Map.Entry<String, Object> operatorKeyValue : value.entrySet()) {
                        fieldName = classParamMap.get(fieldMap.getKey()).getDbColumnName();
                        Class type = fieldNameClass.getType();
                        if (dataClass == Date.class) {
                            Date newDate = new Date(Long.parseLong(String.valueOf(operatorKeyValue.getValue())));
                            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                            objectValue = date.format(newDate);
                            fieldName = "Date(" + fieldName + ")";
                        } else if (type.isEnum()) {
                            objectValue = operatorKeyValue.getValue();

                            if (Status.class.equals(type)
                                    && objectValue.equals("PASSWORD_NOT_SET")) {
                                objectValue = 4;
                            } else if (MediaConversionStatus.class.equals(type)) {
                                objectValue =
                                        ((MediaConversionStatus) Enum.valueOf(type, objectValue.toString())).getValue();
                            } else {
                                int enumValue = Enum.valueOf(type, objectValue.toString()).ordinal();
                                if (type == ContentType.class || type == Access.class) {
                                    enumValue = Enum.valueOf(type, objectValue.toString()).ordinal();
                                } else if (type == Status.class) {
                                    enumValue = ((Status) Enum.valueOf(type,
                                            objectValue.toString())).getValue();
                                } else if (type == State.class) {
                                    enumValue = ((State) Enum.valueOf(type,
                                            objectValue.toString())).getValue();
                                } else if (type == CustomerType.class) {
                                    enumValue = ((CustomerType) Enum.valueOf(type,
                                            objectValue.toString())).getValue();
                                }
                                objectValue = enumValue;
                            }
                        } else {
                            objectValue = operatorKeyValue.getValue();
                        }
                        if (fieldName.equals("state") && objectValue.equals(2)) {
                            stringQuery.append(" AND " + fieldName + " IN (2,6) ");
                        }
                        //Hardcoded condition for "Password Not Set" condition in User.See Line 980 in UserServiceImpl for Reference
                        else if (fieldName.equals("u.status")
                                && objectValue.equals(4)) {
                            stringQuery.append(" AND " + " status Is Null ");
                        } else {
                            stringQuery.append(" AND " + fieldName
                                    + " "
                                    + Operator.getOperatorSymbol(operatorKeyValue.getKey()) + " "
                                    + ":" + fieldNameKey + "_" + operatorKeyValue.getKey());
                            columnValueMap.put(fieldNameKey + "_" + operatorKeyValue.getKey(),
                                    (operatorKeyValue.getKey().equals("lk") ? "%" + objectValue + "%"
                                            : objectValue));
                        }
                    }
                }
            }
        }
        return stringQuery.toString();
    }

    public static ValidationResult advanceSearchCriteriaValidation(
            Map<String, Map<String, Object>> searchQuery,
            Map<String, ClassParam> classParamMap
    ) {

        Map<String, String> errorMap = new HashMap<>();
        ValidationResult validationResult = null;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            for (Map.Entry<String, Map<String, Object>> fieldMap : searchQuery.entrySet()) {
                ClassParam fieldNameClass = classParamMap.get(fieldMap.getKey());
                if (fieldNameClass != null) {
                    Map<String, Object> value = fieldMap.getValue();
                    for (Map.Entry<String, Object> operatorKeyValue : value.entrySet()) {
                        if (Operator.getOperatorSymbol(operatorKeyValue.getKey()) != null) {
                            Class type = fieldNameClass.getType();
                            if (type.isEnum()) {
                                try {
                                    if (!EnumSet.allOf(type)
                                            .contains(Enum.valueOf(type, operatorKeyValue.getValue().toString()))) {
                                        errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                    }
                                } catch (Exception e) {
                                    if (Status.class.equals(type) && operatorKeyValue.getValue()
                                            .equals("PASSWORD_NOT_SET")) {
                                        //Nothing to do
                                    } else {
                                        errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                    }
                                }
                            } else if (fieldNameClass.getType() == Date.class
                                    || fieldNameClass.getType() == Long.class) {
                                try {
                                    Long timeInMillies = Long.parseLong(String.valueOf(operatorKeyValue.getValue()));
                                } catch (Exception e) {
                                    errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                }
                            } else if (fieldNameClass.getType() == String.class) {
                                if (searchQuery.containsKey("from_time") || searchQuery.containsKey("to_time")) {
                                    if (!validateTimeString(operatorKeyValue.getValue().toString())) {
                                        errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                    }
                                } else if (searchQuery.containsKey("layout_tag")) {
                                    if (!(operatorKeyValue.getValue().getClass() == String.class)) {
                                        errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                    }
                                }
                            } else if (fieldNameClass.getType() == Long.class) {
                                if (!(operatorKeyValue.getValue().getClass() == Integer.class
                                        || operatorKeyValue.getValue().getClass() == Long.class)) {
                                    errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                }
                            } else if (fieldNameClass.getType() == Boolean.class) {
                                if (!(operatorKeyValue.getValue().getClass() == Boolean.class)) {
                                    errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                                }
                            } else if (fieldNameClass.getType() != operatorKeyValue.getValue().getClass()) {
                                errorMap.put(fieldMap.getKey(), "Invalid data type  ");
                            }
                        } else {
                            errorMap.put(fieldMap.getKey(), "Invalid Operator type  ");
                        }
                    }
                } else {
                    errorMap.put(fieldMap.getKey(), "Invalid column");
                }
            }
        }
        if (!errorMap.isEmpty()) {
            validationResult = new ValidationResult(errorMap);
        }
        return validationResult;
    }

    public static Map<String, String> validateOperators(Map<String, Map<String, Object>> searchQuery,
                                                        Map<String, String> errorMap) {
        Set<String> keywords = searchQuery.keySet();
        if (keywords.contains("from_date")) {
            validate("from_date", searchQuery.get("from_date"), errorMap);
        }
        if (keywords.contains("to_date")) {
            validate("to_date", searchQuery.get("to_date"), errorMap);
        }
        if (keywords.contains("from_time")) {
            validate("from_time", searchQuery.get("from_time"), errorMap);
        }
        if (keywords.contains("to_time")) {
            validate("to_time", searchQuery.get("to_time"), errorMap);
        }
        if (keywords.contains("location")) {
            validate("location", searchQuery.get("location"), errorMap);
        }
        return errorMap;
    }

    private static void validate(
            String keyword,
            Map<String, Object> operatorMap,
            Map<String, String> errorMap
    ) {
        if (operatorMap.size() != 1) {
            errorMap.put(keyword, "Exists more than once ");
        }
        if (!operatorMap.containsKey("eq")) {
            errorMap.put(keyword, "Only eq operator allowed ");
        }
    }

    private static boolean validateTimeString(String timeIn) {
        if (timeIn.length() != 5) {
            return false;
        }
        String[] hourMinArr = timeIn.split(":");

        if (hourMinArr == null || hourMinArr.length != 2) {
            return false;
        }

        try {
            int hour = Integer.parseInt(hourMinArr[0]);
            if (hour > 23) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        try {
            int min = Integer.parseInt(hourMinArr[1]);
            if (min > 59) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static Map<String, String> validateOperatorsForBetween(
            Map<String, Map<String, Object>> searchQuery, Map<String, String> errorMap) {
        Set<String> keywords = searchQuery.keySet();
        if (keywords.contains("created_date")) {
            validateDateBetween("created_date", searchQuery.get("created_date"), errorMap);
        }
        return errorMap;
    }

    private static void validateDateBetween(String keyword, Map<String, Object> operatorMap,
                                            Map<String, String> errorMap) {
        if (operatorMap.size() > 1) {
            java.sql.Date endDate = null;
            java.sql.Date startDate = null;
            for (Map.Entry<String, Object> operatorMapEntry : operatorMap.entrySet()) {
                if (operatorMapEntry.getKey().equals("lt")) {
                    Long timeInMillies = CommonUtils.getLongValue(operatorMapEntry.getValue());
                    endDate = new java.sql.Date(timeInMillies);
                } else if (operatorMapEntry.getKey().equals("lte")) {
                    Long timeInMillies = CommonUtils.getLongValue(operatorMapEntry.getValue());
                    endDate = new java.sql.Date(timeInMillies);
                } else if (operatorMapEntry.getKey().equals("gt")) {
                    Long timeInMillies = CommonUtils.getLongValue(operatorMapEntry.getValue());
                    startDate = new java.sql.Date(timeInMillies);
                } else if (operatorMapEntry.getKey().equals("gte")) {
                    Long timeInMillies = CommonUtils.getLongValue(operatorMapEntry.getValue());
                    startDate = new java.sql.Date(timeInMillies);
                }
            }
            if (startDate != null && startDate.after(endDate)) {
                errorMap.put(keyword,
                        " 'GreaterThan' condition value always less than 'LessThan' condition value");
            }
        }
    }
}
