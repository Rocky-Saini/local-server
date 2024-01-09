package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableList;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author -Ravi Kumar created on 1/17/2023 5:36 PM
 * @project - Digital Sign-edge
 */
public enum Week {
    SUN("SUN", 1),
    MON("MON", 2),
    TUE("TUE", 3),
    WED("WED", 4),
    THU("THU", 5),
    FRI("FRI", 6),
    SAT("SAT", 7);

    private int order;
    private String value;

    Week(String value, int order) {
        this.value = value;
        this.order = order;
    }

    private static final Map<String, Week> MAP = new HashMap<>();
    private static final ImmutableList<Week> ALL_DAYS_OF_WEEK;

    static {
        List<Week> allDaysOfWeek = new ArrayList<>(7);
        for (Week week : Week.values()) {
            MAP.put(week.value, week);
            allDaysOfWeek.add(week);
        }
        ALL_DAYS_OF_WEEK = ImmutableList.copyOf(allDaysOfWeek);
    }

    @NonNull
    public static ImmutableList<Week> getAllDaysOfWeek() {
        return ALL_DAYS_OF_WEEK;
    }

    @JsonCreator
    public static Week weekFromValue(String value) {
        if (value == null || value.isEmpty()) return null;
        return MAP.get(value);
    }

    public static Week weekFromValue(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) return null;
        return Week.valueOf(dayOfWeek.name().substring(0, 3));
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class WeekDbConverter implements AttributeConverter<Week, String> {

        @Nullable
        public static List<Week> convertDbStringToWeekList(@Nullable String dbValue) {
            if (dbValue == null) {
                return null;
            }
            return Arrays.stream(dbValue.trim().split(","))
                    .map(str -> Week.weekFromValue(str.trim().toUpperCase()))
                    .collect(Collectors.toList());
        }

        @Override
        public String convertToDatabaseColumn(Week week) {
            if (week == null) {
                return null;
            }
            return week.getValue();
        }

        @Override
        public Week convertToEntityAttribute(String s) {
            if (s == null) {
                return null;
            }

            return Stream.of(Week.values())
                    .filter(c -> c.getValue().equals(s))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}

