package com.digital.signage.dto.FfmpegProbResult;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegChapter;
import net.bramp.ffmpeg.probe.FFmpegError;
import net.bramp.ffmpeg.probe.FFmpegFormat;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class FFmpegProbeResult {
    public FFmpegError error;
    public FFmpegFormat format;
    public ArrayList<Stream> streams;
    public List<FFmpegChapter> chapters;
}
