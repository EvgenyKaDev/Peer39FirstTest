package com.peer.testapp.controller.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(
            ConstraintViolationException ex) {
        Error error = new Error(HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage());

        return new ResponseEntity(error, error.getHttpStatus());
    }

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity httpStatusException(HttpStatusException ex){
        Error error = new Error(HttpStatus.valueOf(ex.getStatusCode()),
                ex.getLocalizedMessage());

        return new ResponseEntity(error, error.getHttpStatus());
    }

    @ExceptionHandler(TextExtractorUnexpectedError.class)
    public ResponseEntity textExtractorUnexpectedErrorException(TextExtractorUnexpectedError ex){
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getLocalizedMessage());

        return new ResponseEntity(error, error.getHttpStatus());
    }

}
