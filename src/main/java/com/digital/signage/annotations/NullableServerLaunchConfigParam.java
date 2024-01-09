package com.digital.signage.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author -Ravi Kumar created on 12/29/2022 1:46 AM
 * @project - Digital Sign-edge
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NullableServerLaunchConfigParam {
}
