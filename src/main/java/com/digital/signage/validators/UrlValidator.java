package com.digital.signage.validators;


import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

class UrlValidator extends BaseAnnotationBasedValidator<Url> {
    @Override
    Url castA(Annotation annotation) {
        if (!Url.class.equals(annotation.annotationType())) {
            throw new IllegalArgumentException("annotation should be of Url type");
        }
        return (Url) annotation;
    }

    void validateFieldUsingAnnotation(Field field, Object model,
                                      Url url, Message message, FieldMessageHelper fieldMessageHelper)
            throws IllegalArgumentException, IllegalAccessException {

        String fieldValue = (String) getFieldValueFromObject(model, field);

        String fieldName = field.getName();

        String key = url.jsonKey().trim().isEmpty() ? fieldName : url.jsonKey().trim();

        String originalUrl = fieldValue;

        if (fieldValue != null) {

            if (!fieldValue.startsWith("http://")
                    || !fieldValue.startsWith("https://")) {
                fieldValue = "http://" + fieldValue;
            }

            // validate the url format
            try {
                new URL(fieldValue);
            } catch (MalformedURLException e) {
                String error;
                if (url.malformedUrlError().length == 0) {

                    error = message.get(Message.ValidationMessages.MALFORMED_URL_ERROR, originalUrl);
                } else {

                    error = getErrorFromMessageArray(url.malformedUrlError(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }
        } else {

            if (!url.canBeNull()) {
                // is null
                String error;

                if (url.cannotBeNullErrorMessage().length == 0) {
                    // default message
                    error = message.get(Message.ValidationMessages.NULL_ERROR, key);
                } else {
                    error = getErrorFromMessageArray(url.cannotBeNullErrorMessage(), message);
                }

                fieldMessageHelper.addFieldList(key, error);
            }
        }
    }
}
