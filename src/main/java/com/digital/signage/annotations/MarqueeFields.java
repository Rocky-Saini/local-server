package com.digital.signage.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author -Ravi Kumar created on 1/29/2023 8:36 PM
 * @project - Digital Sign-edge
 */
//@Constraint(validatedBy = MultiFieldContentValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface MarqueeFields {
    String[] value();

    String message() default "{MarqueeFields.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

