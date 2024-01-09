package com.digital.signage.deserializers;

import com.digital.signage.dto.BaseReportRequestDTO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:18 AM
 * @project - Digital Sign-edge
 */
public abstract class BaseDeserializer<T extends BaseReportRequestDTO> extends StdDeserializer<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseDeserializer.class);

    protected BaseDeserializer(Class<?> vc) {
        super(vc);
    }

    protected BaseDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected BaseDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    Date from(String dateString) {
        Date date = null;
        if (dateString != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                date = format.parse(dateString);
            } catch (ParseException e) {
                logger.error("", e);
            }
        }
        return date;
    }

    List<Long> fromInOrEq(JsonNode nodeLevel1) {
        if (nodeLevel1.has("in")) {
            String commaSeparatedDeviceIds = nodeLevel1.get("in").asText();
            try {
                return Stream.of(commaSeparatedDeviceIds.split(","))
                        .map(Long::parseLong)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
            } catch (NumberFormatException | NullPointerException e) {
                logger.error("", e);
            }
        } else if (nodeLevel1.has("eq")) {
            if (nodeLevel1.get("eq").isNumber()
                    || NumberUtils.isNumber(nodeLevel1.get("eq").asText())) {
                Long aLong = nodeLevel1.get("eq").asLong();
                return Collections.singletonList(aLong);
            }
        }
        return new ArrayList<>(0);
    }

    Date lteToDate(JsonNode node) {
        Date date = null;
        if (node.has("to_date")) {
            JsonNode nodeLevel1 = node.get("to_date");
            if (nodeLevel1.has("lte")) {
                String dateString = nodeLevel1.get("lte").asText();
                date = from(dateString);
            }
        }
        return date;
    }

    Date gteFromDate(JsonNode node) {
        Date date = null;
        if (node.has("from_date")) {
            JsonNode nodeLevel1 = node.get("from_date");
            if (nodeLevel1.has("gte")) {
                String dateString = nodeLevel1.get("gte").asText();
                date = from(dateString);
            }
        }
        return date;
    }

    Long eqFromKey(JsonNode node, String key) {
        Long aLong = null;
        if (node.has(key)) {
            JsonNode nodeLevel1 = node.get(key);
            if (nodeLevel1.has("eq")) {
                if (nodeLevel1.get("eq").isNumber()
                        || NumberUtils.isNumber(nodeLevel1.get("eq").asText())) {
                    try {
                        aLong = nodeLevel1.get("eq").asLong();
                    } catch (NumberFormatException | NullPointerException e) {
                        logger.error("", e);
                    }
                }
            }
        }
        return aLong;
    }

    T deserializeBaseModel(T dto, JsonNode node) throws IOException {
        Date fromDate = gteFromDate(node);
        if (fromDate != null) {
            dto.setFromDate(fromDate);
            dto.setFromDateOperator("gte");
        }
        Date toDate = lteToDate(node);
        if (toDate != null) {
            dto.setToDate(toDate);
            dto.setToDateOperator("lte");
        }
        Long locationId = eqFromKey(node, "location");
        if (locationId != null) {
            dto.setLocationIdOperator("eq");
            dto.setLocationId(locationId);
        }
        if (node.has("device")) {
            JsonNode nodeLevel1 = node.get("device");
            if (nodeLevel1.has("eq")) {
                List<Long> deviceList = fromInOrEq(nodeLevel1);
                if (deviceList != null && !deviceList.isEmpty()) {
                    dto.setDeviceList(deviceList);
                    dto.setDeviceListOperator("eq");
                }
            } else if (nodeLevel1.has("in")) {
                List<Long> deviceList = fromInOrEq(nodeLevel1);
                if (deviceList != null && !deviceList.isEmpty()) {
                    dto.setDeviceList(deviceList);
                    dto.setDeviceListOperator("in");
                }
            }
        }
        return dto;
    }
}

