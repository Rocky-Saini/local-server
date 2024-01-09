package com.digital.signage.validators;

import com.digital.signage.exceptions.ValidationException;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:55 PM
 * @project - Digital Sign-edge
 */
public class ValidationUtil {

    private ValidationUtil() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);
    private static final Set<Class<? extends Annotation>> validationAnnotations = new HashSet<>(3);

    private static final Map<Class<? extends Annotation>, BaseAnnotationBasedValidator<? extends Annotation>>
            mapOfValidators = new HashMap<>(3);

    static {
        validationAnnotations.add(IntegerRange.class);
        validationAnnotations.add(LongRange.class);
        validationAnnotations.add(Url.class);

        mapOfValidators.put(IntegerRange.class, new IntegerRangeValidator());
        mapOfValidators.put(LongRange.class, new LongRangeValidator());
        mapOfValidators.put(Url.class, new UrlValidator());
    }

    @SuppressWarnings("squid:S1130")
    public static <T> void validate(
            T model,
            Class<T> classOfT,
            Message message,
            FieldMessageHelper fieldMessageHelper
    ) throws ValidationException {
        logger.debug("validating model={}, classOfT={}", model, classOfT);
        try {
            Field[] fields = classOfT.getDeclaredFields();
            logger.debug("fields size = {}", fields.length);
            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {
                    logger.debug("annotation={}", annotation);
                    logger.debug("annotation.annotationType()={}", annotation.annotationType());
                    if (validationAnnotations.contains(annotation.annotationType())) {
                        logger.debug("annotation is contained in this");
                        // apply valid field so apply validation
                        BaseAnnotationBasedValidator validator =
                                mapOfValidators.get(annotation.annotationType());
                        validator.validateFieldUsingAnnotation(
                                field,
                                model,
                                validator.castA(annotation),
                                message,
                                fieldMessageHelper
                        );
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ValidationException(e);
        }
    }
}
