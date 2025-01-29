package com.example.exam.Exceptions;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found"),
    PRODUCT_NAME_CANNOT_BE_EMPTY("Product Name Cannot Be Empty"),
    PRODUCT_PRICE_CANNOT_BE_NEGATIVE("Product Price Cannot Be Negative"),
    PRODUCT_CATEGORY_NOT_AVAILABLE("Product Category Not Available"),
    PRODUCT_REGION_NOT_AVAILABLE("Product Region Not Available"),
    PRODUCT_CONTAINS_PROFANITY("Product Contains Profanity"),
    PROFANITY_FILTER_NOT_AVAILABLE("Profanity Filter Not Available");
    
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
