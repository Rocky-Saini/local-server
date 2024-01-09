package com.digital.signage.validators;

import com.digital.signage.models.ValidationResult;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:32 PM
 * @project - Digital Sign-edge
 */
public interface Validator {
    <T> ValidationResult validate(T entityModel);
}
