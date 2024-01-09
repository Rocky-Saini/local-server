package com.digital.signage.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author -Ravi Kumar created on 1/21/2023 9:46 PM
 * @project - Digital Sign-edge
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface PdfTitle {
    String title() default "Panasonic Data Report";
}
