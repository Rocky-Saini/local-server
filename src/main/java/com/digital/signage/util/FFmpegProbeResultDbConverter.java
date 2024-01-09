package com.digital.signage.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.StringWriter;

@Component
@Converter(autoApply = true)
public class FFmpegProbeResultDbConverter implements AttributeConverter<FFmpegProbeResult, String> {
    private static ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(FFmpegProbeResultDbConverter.class);

    @SuppressWarnings("squid:S2696")
    @Autowired
    public void setObjectMapperSingleton(ObjectMapper objectMapper) {
        FFmpegProbeResultDbConverter.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(FFmpegProbeResult attribute) {
        if (attribute == null) {
            return null;
        }
        final StringWriter sw = new StringWriter();
        try {
            sw.append(objectMapper.writeValueAsString(attribute));
            logger.info("FFMPEG data:  {}", sw.toString());
        } catch (Exception e) {
            logger.error("", e);
        }
        return sw.toString();
    }

    @Override
    public FFmpegProbeResult convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        FFmpegProbeResult fFmpegProbeResult = null;
        try {
            logger.info("DB data:  {}", dbData);
            fFmpegProbeResult = objectMapper.readValue(dbData, FFmpegProbeResult.class);
        } catch (Exception e) {
            logger.error("", e);
        }
        return fFmpegProbeResult;
    }
}