package com.example.exam.Exceptions;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
