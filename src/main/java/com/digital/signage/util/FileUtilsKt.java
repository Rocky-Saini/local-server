package com.digital.signage.util;

import com.digital.signage.exceptions.NotAFileException;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;

public class FileUtilsKt {
    public static boolean isDir(Path path) {
        return !path.toFile().isFile();
    }

    public static String getFilename(Path path) throws NotAFileException {
        if (!isDir(path)) {
            return FilenameUtils.getBaseName(path.toString()) + "." +
                    FilenameUtils.getExtension(path.toString());
        } else {
            throw new NotAFileException();
        }
    }

    public static String generateBuildFileNameForDownload(BuildOs buildOs, String version) {
        return "build-" + buildOs.name().toLowerCase() + "-" + version + "." + buildOs.getFileExtension();
    }
}
