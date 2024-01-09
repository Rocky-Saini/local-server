package com.digital.signage.util;


import com.digital.signage.dto.FfmpegProbResult.FFmpegProbeResult;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FfmpegUtils {

    public static FFmpegProbeResult parse(net.bramp.ffmpeg.probe.FFmpegProbeResult fFmpegProbeResult, ObjectMapper objectMapper) {
        try {
            FFmpegProbeResult fFmpegProbeResultResult = objectMapper.readValue(objectMapper.writeValueAsString(fFmpegProbeResult), FFmpegProbeResult.class);
            return fFmpegProbeResultResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
