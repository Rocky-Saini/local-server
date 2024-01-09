package com.digital.signage.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:35 AM
 * @project - Digital Sign-edge
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportColumn {
    int order();
    String columnName();
}
