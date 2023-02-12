package com.peer.testapp.validator.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class ValidUrlValidator implements ConstraintValidator<ValidUrl, Set<String>> {
    private String message;

    @Override
    public void initialize(final ValidUrl constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Set<String> urls, final ConstraintValidatorContext context) {
        log.info("Validating number of URL {} ", urls.size());
        boolean validationStatus = true;
        for (String url:urls) {
            boolean isValidUrl = UrlValidator.validate(url);
            if(!isValidUrl){
                validationStatus = false;
                log.error("One or more URL is not valid!");
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate(String.format("%s for URL %s",message, url))
                        .addConstraintViolation();
            }

        }

        return validationStatus;
    }
}
