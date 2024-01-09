package com.digital.signage.util;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class FFMpegUtility {
    public static FFmpeg newFFmpeg(String fFMpegPath) throws IOException {
        return StringUtils.isBlank(fFMpegPath) ? new FFmpeg() : new FFmpeg(fFMpegPath);
    }

    public static FFprobe newFFProbe(String fFMpegPath, String fFProbePath) throws IOException {
        return StringUtils.isBlank(fFMpegPath) ? new FFprobe() : new FFprobe(fFProbePath);
    }
}
