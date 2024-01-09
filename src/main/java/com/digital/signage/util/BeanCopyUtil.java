package com.digital.signage.util;

import com.digital.signage.exceptions.CopyingPropertiesException;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:11 PM
 * @project - Digital Sign-edge
 */
public class BeanCopyUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanCopyUtil.class);

    private BeanCopyUtil() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static void copy(Object dest, Object source) throws CopyingPropertiesException {
        try {
            BeanUtilsBean2.getInstance().copyProperties(dest, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CopyingPropertiesException(e);
        }
    }

    public static void nullAwareBeanCopy(
            @NonNull NullAwareBeanUtilsBean nullAwareBeanUtilsBean,
            @NonNull Object destination,
            @NonNull Object origin
    ) throws CopyingPropertiesException {
        try {
            nullAwareBeanUtilsBean.copyProperties(destination, origin);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("", e);
            throw new CopyingPropertiesException(e);
        }
    }
}
