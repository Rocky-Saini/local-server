package com.digital.signage.util;



import com.digital.signage.exceptions.GenericException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtility {
        public static String getResourceAsString(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("");//TODO define proper custom exception
        }
    }

    public static boolean isPdfValid(InputStream stream) {
        Objects.requireNonNull(stream, "stream is null");
        try (RandomAccessRead randomAccessRead = new RandomAccessBufferedFileInputStream(stream)) {
            new PDFParser(randomAccessRead).parse();
            return true;
        } catch (IOException e) {
            return false;//TODO logging
        }
    }

    public static String removeWhiteSpaces(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }
        String suffix = "\\n";
        if (html.endsWith(suffix)) {
            return html.substring(0, html.length() - suffix.length());
        } else {
            return html;
        }
    }

    public static String fileName(Path filePath) {
        boolean isDirectory = !filePath.toFile().isFile();
        if(!isDirectory) {
            return FilenameUtils.getBaseName(filePath.toString()) + "." +
                    FilenameUtils.getExtension(filePath.toString());
        }
        throw new GenericException("Not a file", HttpStatus.NOT_FOUND);
    }

}
