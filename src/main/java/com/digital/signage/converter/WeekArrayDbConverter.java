package com.digital.signage.converter;

import com.digital.signage.util.Week;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * @author -Ravi Kumar created on 1/25/2023 12:34 AM
 * @project - Digital Sign-edge
 */
@Converter(autoApply = true)
public class WeekArrayDbConverter implements AttributeConverter<Week[], String> {
    @Override
    public String convertToDatabaseColumn(Week[] attribute) {
        if (attribute == null) return null;
        if (attribute.length == 0) return "";
        return String.join(",", Arrays.stream(attribute)
                .map(Week::getValue)
                .collect(Collectors.toCollection(LinkedHashSet::new)));
    }

    @Override
    public Week[] convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        if (dbData.trim().isEmpty()) return new Week[]{};
        return Arrays.stream(dbData.split(","))
                .map(s -> Week.valueOf(s.trim()))
                .toArray(Week[]::new);
    }
}

