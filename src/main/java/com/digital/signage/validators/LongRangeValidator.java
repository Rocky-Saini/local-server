package com.digital.signage.validators;


import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class LongRangeValidator extends BaseAnnotationBasedValidator<LongRange> {
    @Override
    LongRange castA(Annotation annotation) {
        if (!LongRange.class.equals(annotation.annotationType())) {
            throw new IllegalArgumentException("annotation should be of LongRange type");
        }
        return (LongRange) annotation;
    }

    void validateFieldUsingAnnotation(Field field, Object model,
                                      LongRange longRange, Message message, FieldMessageHelper fieldMessageHelper)
            throws IllegalArgumentException, IllegalAccessException {

        Long fieldValue = (Long) getFieldValueFromObject(model, field);

        String fieldName = field.getName();

        String key =
                longRange.jsonKey().trim().isEmpty() ? fieldName : longRange.jsonKey().trim();

        if (fieldValue != null) {

            if (longRange.isNegativeValueAllowed() && fieldValue.compareTo(0L) < 0) {
                // is negative value and negative value is allowed. So skip next min and max check
            } else {

                if (fieldValue.compareTo(longRange.max()) > 0) {
                    // error message
                    // apply our error message
                    String error;

                    if (longRange.maxErrorMessage().length == 0) {
                        // default message
                        error = message.get(Message.ValidationMessages.MAX, key, longRange.max());
                    } else {
                        error = getErrorFromMessageArray(longRange.maxErrorMessage(), message);
                    }

                    fieldMessageHelper.addFieldList(key, error);
                }

                if (fieldValue < longRange.min()) {
                    // error message
                    // apply our error message
                    String error;

                    if (longRange.minErrorMessage().length == 0) {
                        // default message
                        error = message.get(Message.ValidationMessages.MIN, key, longRange.min());
                    } else {
                        error = getErrorFromMessageArray(longRange.minErrorMessage(), message);
                    }

                    fieldMessageHelper.addFieldList(key, error);
                }
            }
        } else {

            // is null
            if (!longRange.canBeNull()) {
                // but cannot be null
                String error;

                if (longRange.cannotBeNullErrorMessage().length == 0) {
                    // default message
                    error = message.get(Message.ValidationMessages.NULL_ERROR, key);
                } else {
                    error = getErrorFromMessageArray(longRange.cannotBeNullErrorMessage(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }
        }
    }
}
