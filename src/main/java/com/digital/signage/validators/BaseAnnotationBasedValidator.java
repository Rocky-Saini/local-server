package com.digital.signage.validators;

import com.digital.signage.exceptions.ValidationException;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:57 PM
 * @project - Digital Sign-edge
 */
abstract class BaseAnnotationBasedValidator<A extends Annotation> {

    static final String MESSAGE_MISSING = "message.missing";

    abstract A castA(Annotation annotation);

    String getErrorFromMessageArray(String[] array, Message message) {
        String error;
        if (array.length == 0) {
            error = message.get(MESSAGE_MISSING);
        } else if (array.length == 1) {
            error = message.get(array[0]);
        } else if (array.length == 2) {
            error = message.get(array[0], array[1]);
        } else {
            Object[] arr = Arrays.copyOfRange(array, 1, array.length);
            error = message.get(array[0], arr);
        }
        return error;
    }

    @SuppressWarnings({"squid:S1130", "squid:S3011", "squid:S1874"})
    Object getFieldValueFromObject(Object model, Field field) throws ValidationException {
        try {
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
            Object result = field.get(model);
            if (!isAccessible) {
                field.setAccessible(false);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new ValidationException(
                    "Field '" + field.getName() + "' should not be private in class '"
                            + model.getClass().getSimpleName() + "'", e);
        }
    }

    @SuppressWarnings("squid:S1130")
    abstract void validateFieldUsingAnnotation(
            Field field,
            Object model,
            A a,
            Message message,
            FieldMessageHelper fieldMessageHelper
    ) throws IllegalArgumentException, IllegalAccessException;
}

