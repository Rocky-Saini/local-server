package com.digital.signage.deserializers;

import com.digital.signage.models.PanelLogReportRequestDTO;
import com.digital.signage.util.ReportConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:17 AM
 * @project - Digital Sign-edge
 */
public class PanelLogRequestDTODeserializer extends BaseDeserializer<PanelLogReportRequestDTO> {
    public PanelLogRequestDTODeserializer() {
        super(PanelLogReportRequestDTO.class);
    }

    protected PanelLogRequestDTODeserializer(Class<?> vc) {
        super(vc);
    }

    protected PanelLogRequestDTODeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PanelLogRequestDTODeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public PanelLogReportRequestDTO deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        PanelLogReportRequestDTO dto = new PanelLogReportRequestDTO();
        deserializeBaseModel(dto, node);
        if (node.has(ReportConstants.PANEL_ID_KEY)) {
            JsonNode nodeLevel1 = node.get(ReportConstants.PANEL_ID_KEY);
            if (nodeLevel1.has("eq")) {
                dto.setPanelIdOperator("eq");
                if (nodeLevel1.get("eq").isNumber()) {
                    dto.setPanelId(Long.valueOf(nodeLevel1.get("eq").asText()));
                }
            }
        }
        if (node.has(ReportConstants.PANEL_STATUS_KEY)) {
            JsonNode nodeLevel1 = node.get(ReportConstants.PANEL_STATUS_KEY);
            if (nodeLevel1.has("eq")) {
                dto.setPanelStatusOperator("eq");
                if (nodeLevel1.get("eq").isTextual()) {
                    String temp = nodeLevel1.get("eq").asText();
                    if ("DATA_NOT_AVAILABLE".equals(temp)) {
                        dto.setPanelStatus("NOT_AVAILABLE");
                    } else {
                        dto.setPanelStatus(temp);
                    }
                }
            }
        }

        if (node.has(ReportConstants.PANEL_IP_KEY)) {
            JsonNode nodeLevel1 = node.get(ReportConstants.PANEL_IP_KEY);
            if (nodeLevel1.has("eq")) {
                if (nodeLevel1.get("eq").isTextual()) {
                    dto.setPanelIp(nodeLevel1.get("eq").asText());
                }
            }
        }

        return dto;
    }
}
