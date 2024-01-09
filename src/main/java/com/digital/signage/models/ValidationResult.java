package com.digital.signage.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/21/2023 7:33 PM
 * @project - Digital Sign-edge
 */
public class ValidationResult {
    private Map<String, String> errorMap;
    private List<FieldMessage> fieldMessages;

    public ValidationResult(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public ValidationResult(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public List<FieldMessage> getFieldMessageList() {
        if (fieldMessages != null) {
            return fieldMessages;
        } else {
            List<FieldMessage> fieldMessages2 = new ArrayList<>(this.getErrorMap().size());
            for (Map.Entry<String, String> entry : this.getErrorMap().entrySet()) {
                fieldMessages2.add(new FieldMessage(entry.getKey(), entry.getValue()));
            }
            return fieldMessages2;
        }
    }
}
