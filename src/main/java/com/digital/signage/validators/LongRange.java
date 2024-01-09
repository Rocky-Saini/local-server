package com.digital.signage.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 12/27/2022 8:50 PM
 * @project - Digital Sign-edge
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LongRange {
    long min();

    long max();

    boolean canBeNull() default true;

    String[] maxErrorMessage() default {};

    String[] minErrorMessage() default {};

    String[] cannotBeNullErrorMessage() default {};

    String jsonKey() default "";

    boolean isNegativeValueAllowed() default false;
}

