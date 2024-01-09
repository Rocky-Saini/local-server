package com.digital.signage.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.digital.signage.util.ApplicationConstants.THUMBNAIL_EXTENSION;

@Service
@Slf4j
public class ThumbnailsHelper {

    public static final int THUMBNAIL_WIDTH = 250;
    public static final int THUMBNAIL_HEIGHT = 250;

    public String getThumbnailFromFile(String fileWithPath) throws IOException {
        Path filePath = Paths.get(fileWithPath);
        Path parentDirectory = filePath.getParent();
        String fileName = FileUtilsKt.getFilename(filePath);
        final String thumbnailFileName = attemptThumbnailCreation(parentDirectory, fileName, 2);
        if (thumbnailFileName != null && !thumbnailFileName.isEmpty()) {
            //Go ahead try to create thumbnail in parent directory
            File sourceFile = new File(fileWithPath);
            Thumbnails.of(sourceFile)
                    .size(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)
                    .keepAspectRatio(true)
                    .imageType(BufferedImage.TYPE_INT_ARGB)
                    .outputFormat(THUMBNAIL_EXTENSION)
                    .toFiles(parentDirectory.toFile(), new Rename() {
                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return thumbnailFileName;
                        }
                    });
            return String.join(File.separator, thumbnailFileName);
        }
        //No thumbnailFileName. Avoid errors and return null explicitly
        return null;
    }

    /**
     * returns thumbnail file name (not its entire path)
     * File is created in parentDirectory passed as argument
     *
     * if parentDirectory = /abc/xyz
     * sourceFileName = my-cool-image.jpg
     * thumbFilename = my-cool-image-thumb.png
     *
     * then thumbnail will be created in path
     * /abc/xyz/my-cool-image-thumb.png
     *
     */
    private String attemptThumbnailCreation(Path parentDirectory, String sourceFileName,
                                            int retryAttempts) throws IOException {
        String[] fileNameComponents = sourceFileName.split(Pattern.quote("."));
        String thumbFilename = null;
        do {
            retryAttempts--;
            thumbFilename = fileNameComponents[0] + "-thumb" + "." + THUMBNAIL_EXTENSION;
            File thumbFile = Paths.get(parentDirectory.toString(), thumbFilename).toFile();
            if (thumbFile.exists()) {
                // image already exists. And therefore belongs to another content
                // check for a new filename by continuing
                continue;
            } else {
                boolean isFileCreated = thumbFile.createNewFile();
                log.debug("isFileCreated = " + isFileCreated);
                break;
            }
        } while (retryAttempts != 0);
        return thumbFilename;
    }

    /**
     * only creates file but does not create a thumb
     *
     * @param retryAttempts number of attempts
     * @param destinationThumbDirPath destination thum dir
     * @return created empty file name not the path
     */
    private String attemptThumbnailCreation(int retryAttempts, Path destinationThumbDirPath)
            throws IOException {
        assert retryAttempts > 0;
        String fileName = null;
        do {
            fileName = generateRandomThumbnailFileName();
            File file = Paths.get(destinationThumbDirPath.toString(), fileName).toFile();
            if (file.exists()) {
                // image already exists. And therefore belongs to another content
                // check for a new filename by continuing
            } else {
                boolean isFileCreated = file.createNewFile();
                log.debug("isFileCreated = " + isFileCreated);
                break;
            }
            retryAttempts--;
        } while (retryAttempts != 0);
        return fileName;
    }

    private String generateRandomThumbnailFileName() {
        return UUID.randomUUID().toString() + "." + THUMBNAIL_EXTENSION;
    }
}