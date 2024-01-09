package com.digital.signage.util;

public class MediaDispositionUtility {
    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static String mediaDispositionHeader(Boolean download, String fileName) {
        return (download == null || !download) ? "inline; filename=\"" + fileName + "\"" : "attachment; filename=\"" + fileName + "\"";
    }
}
