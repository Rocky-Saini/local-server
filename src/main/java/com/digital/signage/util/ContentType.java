package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:40 AM
 * @project - Digital Sign-edge
 */
public enum ContentType {

    VIDEO(0),    // 0
    AUDIO(1),    // 1
    IMAGE(2),    // 2
    FLASH(3),    // 3
    PDF(4),      // 4
    DOC(5),      // 5
    PPT(6),      // 6
    TEXT(7),     // 7
    RSS(8),      // 8
    TWITTER(9),  // 9
    FACEBOOK(10), // 10
    URL(11),      // 11
    HTML(12),     // 12
    STREAM_URL(13); //13

    private int value;

    ContentType(int value) {
        this.value = value;
    }

    private static final Map<Integer, ContentType> MAP = new HashMap<>(ContentType.values().length);

    static {
        for (ContentType contentType : ContentType.values()) {
            MAP.put(contentType.value, contentType);
        }
    }

    public static ContentType fromDbValue(int contentTypeValue) {
        return MAP.get(contentTypeValue);
    }

    public int getDBValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ContentTypeConverter implements AttributeConverter<ContentType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ContentType contentType) {
            if (null == contentType) return null;
            return contentType.getDBValue();
        }

        @Override
        public ContentType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return ContentType.fromDbValue(dbData);
        }
    }

    public static List<ContentTypeResponseDTO> getContentTypes() {
        List<ContentTypeResponseDTO> contentTypes = new ArrayList<>();
        for (ContentType contentType : ContentType.values()) {
            //String contentTypeName = contentType.name();
            contentTypes.add(new ContentTypeResponseDTO(contentType,
                    (contentType.equals(ContentType.TWITTER) || contentType.equals(
                            ContentType.FACEBOOK))));
        }
        return contentTypes;
    }

    public static List<ContentTypeResponseDTO> getBasicContentTypesAsMyResult() {
        List<ContentTypeResponseDTO> contentTypes = new ArrayList<>();
        contentTypes.add(new ContentTypeResponseDTO(ContentType.VIDEO, false, true));
        contentTypes.add(new ContentTypeResponseDTO(ContentType.IMAGE, false, true));
        contentTypes.add(new ContentTypeResponseDTO(ContentType.AUDIO, false, true));
        contentTypes.add(new ContentTypeResponseDTO(ContentType.TEXT, false, true));
        contentTypes.add(new ContentTypeResponseDTO(ContentType.PDF, false, true));
        return contentTypes;
    }

    public static List<ContentType> getBasicCustomerTypeContentTypes() {
        List<ContentType> contentTypes = new ArrayList<>();
        contentTypes.add(ContentType.VIDEO);
        contentTypes.add(ContentType.IMAGE);
        contentTypes.add(ContentType.AUDIO);
        contentTypes.add(ContentType.TEXT);
        contentTypes.add(ContentType.PDF);
        return contentTypes;
    }

    public static class ContentTypeResponseDTO {
        private ContentType contentType;
        @JsonProperty("isMicroblogContentType")
        private boolean isMicroBlogContentType;
        private boolean isReadyForUse;

        public ContentTypeResponseDTO(
                ContentType contentType,
                boolean isMicroBlogContentType
        ) {
            this.contentType = contentType;
            this.isMicroBlogContentType = isMicroBlogContentType;
        }

        public ContentTypeResponseDTO(
                ContentType contentType,
                boolean isMicroBlogContentType,
                boolean isReadyForUse
        ) {
            this.contentType = contentType;
            this.isMicroBlogContentType = isMicroBlogContentType;
            this.isReadyForUse = isReadyForUse;
        }

        public boolean getIsReadyForUse() {
            return isReadyForUse;
        }

        public void setIsReadyForUse(boolean isReadyForUse) {
            this.isReadyForUse = isReadyForUse;
        }

        public boolean getIsMicroBlogContentType() {
            return isMicroBlogContentType;
        }

        public void setIsMicroBlogContentType(boolean isMicroBlogContentType) {
            this.isMicroBlogContentType = isMicroBlogContentType;
        }

        public ContentType getContentType() {
            return contentType;
        }

        public void setContentType(ContentType contentType) {
            this.contentType = contentType;
        }
    }
}
