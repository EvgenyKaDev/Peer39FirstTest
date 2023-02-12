package com.peer.testapp.controller.exception;

public class TextExtractorUnexpectedError extends RuntimeException {
    public TextExtractorUnexpectedError(String message) {
        super(message);
    }
}
