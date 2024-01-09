package com.digital.signage.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ColumnsOrdersAndFields {
    private Map<Integer, Field> orderAndFieldMap;
    private List<Field> orderedFields;
    private Map<Integer, String> orderAndColumnNameMap;
    private List<Integer> orderList;

    public ColumnsOrdersAndFields(
            Map<Integer, Field> orderAndFieldMap,
            List<Field> orderedFields,
            Map<Integer, String> orderAndColumnNameMap,
            List<Integer> orderList
    ) {
        this.orderAndFieldMap = orderAndFieldMap;
        this.orderedFields = orderedFields;
        this.orderAndColumnNameMap = orderAndColumnNameMap;
        this.orderList = orderList;
    }

    public Map<Integer, Field> getOrderAndFieldMap() {
        return orderAndFieldMap;
    }

    public List<Field> getOrderedFields() {
        return orderedFields;
    }

    public Map<Integer, String> getOrderAndColumnNameMap() {
        return orderAndColumnNameMap;
    }

    public List<Integer> getOrderList() {
        return orderList;
    }
}