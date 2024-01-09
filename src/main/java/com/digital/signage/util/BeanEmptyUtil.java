package com.digital.signage.util;

import com.digital.signage.annotations.NotEmptyIfNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;

/**
 * @author -Ravi Kumar created on 1/24/2023 11:35 PM
 * @project - Digital Sign-edge
 */
public class BeanEmptyUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanEmptyUtil.class);

    private BeanEmptyUtil() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static <T> boolean isBeanEmpty(@NonNull T object, @NonNull Class<T> classOfObject) {
        assert object != null;
        assert classOfObject != null;
        Field[] fields = classOfObject.getDeclaredFields();
        boolean isBeanEmpty = true;
        for (Field field : fields) {
            NotEmptyIfNotNull n = field.getAnnotation(NotEmptyIfNotNull.class);
            if (n != null) {
                if (n.notEmptyIfNotNull()) {
                    try {
                        boolean isAccessible = field.isAccessible();
                        if (!isAccessible) {
                            field.setAccessible(true);
                        }
                        Object value = field.get(object);
                        if (!isAccessible) {
                            field.setAccessible(isAccessible);
                        }
                        if (value != null) {
                            isBeanEmpty = false;
                            break;
                        }
                    } catch (IllegalAccessException e) {
                        logger.error("", e);
                    }
                }
            }
        }
        return isBeanEmpty;
    }
}
