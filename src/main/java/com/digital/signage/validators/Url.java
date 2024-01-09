package com.digital.signage.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 12/27/2022 9:24 PM
 * @project - Digital Sign-edge
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Url {
    String jsonKey() default "";

    boolean canBeNull() default true;

    String[] malformedUrlError() default {};

    String[] cannotBeNullErrorMessage() default {};
}
