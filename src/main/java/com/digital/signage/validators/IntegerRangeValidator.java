package com.digital.signage.validators;


import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class IntegerRangeValidator extends BaseAnnotationBasedValidator<IntegerRange> {

    @Override
    IntegerRange castA(Annotation annotation) {
        if (!IntegerRange.class.equals(annotation.annotationType())) {
            throw new IllegalArgumentException("annotation should be of IntegerRange type");
        }
        return (IntegerRange) annotation;
    }

    @SuppressWarnings("squid:S1130")
    void validateFieldUsingAnnotation(
            Field field,
            Object model,
            IntegerRange integerRange,
            Message message,
            FieldMessageHelper fieldMessageHelper
    ) throws IllegalArgumentException, IllegalAccessException {

        Integer fieldValue = (Integer) getFieldValueFromObject(model, field);

        String fieldName = field.getName();

        String key =
                integerRange.jsonKey().trim().isEmpty() ? fieldName : integerRange.jsonKey().trim();

        if (fieldValue != null) {

            if (fieldValue > integerRange.max()) {
                // error message
                // apply our error message
                // apply our error message
                String error;

                if (integerRange.maxErrorMessage().length == 0) {
                    // default message
                    error = message.get(Message.ValidationMessages.MAX, key, integerRange.max());
                } else {
                    error = getErrorFromMessageArray(integerRange.maxErrorMessage(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }

            if (fieldValue < integerRange.min()) {
                // error message
                // apply our error message
                String error;

                if (integerRange.minErrorMessage().length == 0) {
                    // default message
                    error = message.get(Message.ValidationMessages.MIN, key, integerRange.min());
                } else {
                    error = getErrorFromMessageArray(integerRange.minErrorMessage(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }
        } else {

            // is null
            if (!integerRange.canBeNull()) {
                // but cannot be null
                String error;

                if (integerRange.cannotBeNullErrorMessage().length == 0) {
                    // default message
                    error = message.get(Message.ValidationMessages.NULL_ERROR, key);
                } else {
                    error = getErrorFromMessageArray(integerRange.cannotBeNullErrorMessage(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }
        }
    }
}
