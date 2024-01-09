package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:50 AM
 * @project - Digital Sign-edge
 */
public enum State {

    DRAFT(1), SUBMITTED(2), APPROVED(3), REJECTED(4), PUBLISHED(5), PENDING_FOR_APPROVAL(6);

    private final int value;

    State(int value) {
        this.value = value;
    }

    private final static Map<Integer, State> MAP_VALUE_TO_STATE = new HashMap<>();

    static {
        for (State state : State.values()) {
            MAP_VALUE_TO_STATE.put(state.value, state);
        }
    }

    public static State valueOf(int state) {
        return MAP_VALUE_TO_STATE.get(state);
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getStateForJson() {
        return State.PENDING_FOR_APPROVAL.value == value ?
                State.SUBMITTED.name() : State.valueOf(value).name();
    }

    @Converter(autoApply = true)
    public static class StateConverter implements AttributeConverter<State, Integer> {

        @Override
        public Integer convertToDatabaseColumn(State state) {
            if (null == state) return null;
            return state.getValue();
        }

        @Override
        public State convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return State.valueOf(dbData);
        }
    }
}
