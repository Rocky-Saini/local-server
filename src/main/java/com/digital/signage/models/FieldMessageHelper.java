package com.digital.signage.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/16/2023 5:37 PM
 * @project - Digital Sign-edge
 */
public class FieldMessageHelper {

    private final List<FieldMessage> list;

    public FieldMessageHelper() {
        list = new ArrayList<>();
    }

    public FieldMessageHelper addFieldList(String field, String message) {
        list.add(new FieldMessage(field, message));
        return this;
    }

    public FieldMessageHelper addFieldList(FieldMessage fieldMessage) {
        list.add(fieldMessage);
        return this;
    }

    public List<FieldMessage> get() {
        return list;
    }
}
