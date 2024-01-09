package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.NonNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:39 AM
 * @project - Digital Sign-edge
 */
public enum BuildOs {

    ANDROID(0, "ANDROID", "android", "apk"),
    ANDROID_WATCH_DOG(5, "ANDROID_WATCH_DOG", "android-watch-dog", "apk"),
    WINDOWS(1, "WINDOWS", "windows", "zip"),
    DESKTOP_APP_SERVER(2, "DESKTOP_APP_SERVER", "desktop-server", "zip"),
    DESKTOP_APP_CLIENT(3, "DESKTOP_APP_CLIENT", "desktop-client", "zip"),
    DESKTOP_APP_NATIVE(4, "DESKTOP_APP_NATIVE", "desktop-native", "zip"),
    ANDROID_TV(6, "ANDROID_TV", "android-tv", "apk");
    public static final Set<BuildOs> LINUX_APP_SET = new HashSet<>(3);
    private static final Set<BuildOs> ANDROID_BUILD_OS_SET = new HashSet<>(2);
    private static final Set<BuildOs> WINDOWS_BUILD_OS_SET = new HashSet<>(1);
    private static final Set<BuildOs> ANDROID_TV_BUILD_OS_SET = new HashSet<>(2);

    @NonNull
    public static DeviceOs getDeviceOsFromBuildOs(@NonNull BuildOs buildOs)
            throws IllegalArgumentException {
        if (LINUX_APP_SET.contains(buildOs)) {
            return DeviceOs.DESKTOP;
        } else if (ANDROID_BUILD_OS_SET.contains(buildOs)) {
            return DeviceOs.ANDROID;
        } else if (ANDROID_TV_BUILD_OS_SET.contains(buildOs)) {
            return DeviceOs.ANDROID_TV;
        } else if (WINDOWS_BUILD_OS_SET.contains(buildOs)) {
            return DeviceOs.WINDOWS;
        } else {
            throw new IllegalArgumentException("Invalid build OS");
        }
    }

    private final int dbValue;
    private final String buildDirName;
    private final String jsonValue;
    private final String fileExtension;

    private static final Map<Integer, BuildOs> DB_MAP = new HashMap<>(5);
    private static final Map<String, BuildOs> JSON_MAP = new HashMap<>(5);

    static {
        LINUX_APP_SET.add(DESKTOP_APP_NATIVE);
        LINUX_APP_SET.add(DESKTOP_APP_CLIENT);
        LINUX_APP_SET.add(DESKTOP_APP_SERVER);

        ANDROID_BUILD_OS_SET.add(ANDROID);
        ANDROID_BUILD_OS_SET.add(ANDROID_WATCH_DOG);

        ANDROID_TV_BUILD_OS_SET.add(ANDROID_TV);

        WINDOWS_BUILD_OS_SET.add(WINDOWS);

        for (BuildOs buildOs : BuildOs.values()) {
            DB_MAP.put(buildOs.dbValue, buildOs);
            JSON_MAP.put(buildOs.jsonValue, buildOs);
        }
    }

    BuildOs(
            int dbValue,
            String jsonValue,
            String buildDirName,
            String fileExtension
    ) {
        this.dbValue = dbValue;
        this.jsonValue = jsonValue;
        this.buildDirName = buildDirName;
        this.fileExtension = fileExtension;
    }

    @JsonCreator
    public static BuildOs jsonCreate(String jsonValue) {
        return JSON_MAP.get(jsonValue);
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }

    public static BuildOs valueOf(int desktopAppEnum) {
        return DB_MAP.get(desktopAppEnum);
    }

    public int getDbValue() {
        return dbValue;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getBuildDirName() {
        return buildDirName;
    }

    @Converter(autoApply = true)
    public static class BuildOsDbConverter implements AttributeConverter<BuildOs, Integer> {

        @Override
        public Integer convertToDatabaseColumn(BuildOs buildOs) {
            if (null == buildOs) return null;
            return buildOs.getDbValue();
        }

        @Override
        public BuildOs convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return BuildOs.valueOf(dbData);
        }
    }

}
