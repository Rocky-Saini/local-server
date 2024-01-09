package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum FileType {
    ZIP(1, "zip"),
    TAR(2, "tar.gz");

    private int value;
    private String fileExtension;

    FileType(int value, String fileExtension) {
        this.value = value;
        this.fileExtension = fileExtension;
    }

    private static final Map<Integer, FileType> MAP = new HashMap<>();

    static {
        for (FileType fileType : FileType.values()) {
            MAP.put(fileType.value, fileType);
        }
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public int getValue() {
        return value;
    }

    public static FileType valueOf(int intValue) {
        return MAP.get(intValue);
    }

    @JsonCreator
    public static FileType valueOfEnum(String fileType) {
        return FileType.valueOf(fileType);
    }

    @JsonValue
    public String getJsonValue() {
        return MAP.get(value).name();
    }

    @Converter(autoApply = true)
    public static class FileTypeConverter implements AttributeConverter<FileType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(FileType fileType) {
            if (null == fileType) return null;
            return fileType.getValue();
        }

        @Override
        public FileType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return FileType.valueOf(dbData);
        }
    }
}

