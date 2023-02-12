package com.peer.testapp.validator.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidUrlValidator.class)
@Target({PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidUrl {
    String message() default "The URL string does not comply with the rules.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}