package com.digital.signage.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 2/1/2023 12:45 AM
 * @project - Digital Sign-edge
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportTimeFormat {
    int beginIndex();

    int lastIndex();
}

