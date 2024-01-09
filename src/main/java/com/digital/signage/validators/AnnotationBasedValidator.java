package com.digital.signage.validators;

import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface AnnotationBasedValidator<T> {
    FieldMessageHelper validate(Field[] fields, T model, Message message,
                                FieldMessageHelper fieldMessageHelper, Class<? extends Annotation> classOfA)
            throws IllegalArgumentException, IllegalAccessException;
}
