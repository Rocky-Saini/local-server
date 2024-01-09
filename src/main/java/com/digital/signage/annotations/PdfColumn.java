package com.digital.signage.annotations;

import static com.digital.signage.util.ReportConstants.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:27 AM
 * @project - Digital Sign-edge
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PdfColumn {
    int order();
    String columnName();
    int width() default PDF_COLUMN_WIDTH;
}
