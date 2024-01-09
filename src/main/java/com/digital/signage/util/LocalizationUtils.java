package com.digital.signage.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LocalizationUtils {
    private Map<String, TreeMap<String, String>> allLanguagesMap = new HashMap<>();

    private List<String> supportedLanguages = List.of(ApplicationConstants.EN);
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        allLanguagesMap.put(ApplicationConstants.EN, new TreeMap<>());
        supportedLanguages.forEach(s -> {
            try {
                InputStream inputStream = new ClassPathResource("localization/" + s + ".json").getInputStream();
                allLanguagesMap.put(s, objectMapper.readValue(inputStream, new TypeReference<TreeMap<String, String>>() {}));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String getLocalizedValueAgainstKeyByDefaultLaunguage(String key) {
        return getLocalizedValueAgainstKey(key, getDefaultLanguage());
    }

    public String getLocalizedValueAgainstKey(String key, String language) {
        if(StringUtils.isBlank(key))
            return key;
        if(!allLanguagesMap.containsKey(language))
            throw new IllegalArgumentException("Language not supported");
        Map<String, String> valueMap = allLanguagesMap.get(language);
        if(key.contains(",")) {
            return Arrays.stream(key.split(",")).map(s ->
                    valueMap.getOrDefault(s.trim(), s.trim())).collect(Collectors.joining(","));//TODO value check in detail
        }
        return allLanguagesMap.get(language).getOrDefault(key, key);
    }


    public String getDefaultLanguage() {
        return ApplicationConstants.EN;
    }
}
