package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:04 AM
 * @project - Digital Sign-edge
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldMessage {
    private Long id;
    private String field;
    private String message;

    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public FieldMessage(String message) {
        this.field = "ids";
        this.message = message;
    }

    public FieldMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FieldMessage{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
