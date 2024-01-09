package com.digital.signage.util;

import java.util.Map;

public class EmailUtils {

    public static String subjectReplacer(String subject, Map<String, String> keyValueMap) {
         String s = subject;
         for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
             String key = entry.getKey();
             String value = entry.getValue();
             s = s.replace(key, value);
         }
        return s;
    }
}
