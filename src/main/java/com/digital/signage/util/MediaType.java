package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public enum MediaType {
    VIDEO(0),
    AUDIO(1),
    IMAGE(2),
    FLASH(3),
    PDF(4),
    DOC(5),
    PPT(6),
    TEXT(7),
    RSS(8),
    TWITTER(9),
    FACEBOOK(10),
    URL(11),
    HTML(12),
    STREAM_URL(13);

    @Getter
    private final int value;

    MediaType(int value) {
        this.value = value;
    }

    public static List<MediaTypeDetail> basicMediaTypes() {
        return new ArrayList<>() {{
            add(new MediaTypeDetail(VIDEO, false, true));
            add(new MediaTypeDetail(IMAGE, false, true));
            add(new MediaTypeDetail(AUDIO, false, true));
            add(new MediaTypeDetail(TEXT, false, true));
            add(new MediaTypeDetail(PDF, false, true));
        }};
    }

    public static List<MediaType> getBasicCustomerTypeMediaTypes() {
        List<MediaType> contentTypes = new ArrayList<>();
        contentTypes.add(MediaType.VIDEO);
        contentTypes.add(MediaType.IMAGE);
        contentTypes.add(MediaType.AUDIO);
        contentTypes.add(MediaType.TEXT);
        contentTypes.add(MediaType.PDF);
        return contentTypes;
    }

    public static List<MediaTypeDetail> mediaTypes() {
        return Arrays.stream(MediaType.values())
                .map(mediaType -> MediaTypeDetail.builder()
                        .isMicroBlogMediaType(mediaType.equals(MediaType.TWITTER) || mediaType.equals(MediaType.FACEBOOK))
                        .mediaType(mediaType)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<MediaType> mediaTypesRequiredToSetDuration() {
        return List.of(MediaType.FACEBOOK, MediaType.TWITTER, MediaType.PPT, MediaType.DOC, MediaType.PDF, MediaType.RSS,
                MediaType.TEXT, MediaType.HTML, MediaType.IMAGE);
    }

    public boolean isMediaTypeFacebook() {
        return MediaType.FACEBOOK.equals(this);
    }

    public boolean isMediaTypeTwitter() {
        return MediaType.TWITTER.equals(this);
    }

    public boolean isMediaTypePdf() {
        return MediaType.PDF.equals(this);
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MediaTypeDetail {
        private MediaType mediaType;
        @JsonProperty("isMicroblogMediaType")
        private boolean isMicroBlogMediaType;
        private boolean readyForUse;
    }
}
