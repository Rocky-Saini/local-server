package com.digital.signage.deserializers;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.util.ReportConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:10 AM
 * @project - Digital Sign-edge
 */
public class DeviceLogRequestDTODeserializer extends BaseDeserializer<DeviceLogReportRequestDTO> {
    public DeviceLogRequestDTODeserializer() {
        super(DeviceLogReportRequestDTO.class);
    }

    protected DeviceLogRequestDTODeserializer(Class<?> vc) {
        super(vc);
    }

    protected DeviceLogRequestDTODeserializer(JavaType valueType) {
        super(valueType);
    }

    protected DeviceLogRequestDTODeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public DeviceLogReportRequestDTO deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        DeviceLogReportRequestDTO dto = new DeviceLogReportRequestDTO();
        deserializeBaseModel(dto, node);
        if (node.has(ReportConstants.LOCATION_KEY)) {
            JsonNode nodeLevel1 = node.get(ReportConstants.LOCATION_KEY);
            if (nodeLevel1.has("eq")) {
                dto.setLocationIdOperator("eq");
                if (nodeLevel1.get("eq").isTextual()) {
                    dto.setLocationId(Long.valueOf(nodeLevel1.get("eq").asText()));
                }
            }
        }
        if (node.has(ReportConstants.DEVICE_STATUS_KEY)) {
            JsonNode nodeLevel1 = node.get(ReportConstants.DEVICE_STATUS_KEY);
            if (nodeLevel1.has("eq")) {
                dto.setDeviceStatusOperator("eq");
                if (nodeLevel1.get("eq").isTextual()) {
                    String temp = nodeLevel1.get("eq").asText();
                    if ("DATA_NOT_AVAILABLE".equals(temp)) {
                        dto.setDeviceStatus("NOT_AVAILABLE");
                    } else {
                        dto.setDeviceStatus(temp);
                    }
                }
            }
        }
        return dto;
    }
}
