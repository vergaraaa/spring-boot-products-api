package com.example.exam.Exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotValidException extends CustomBaseException {
    public ProductNotValidException(SimpleResponse simpleResponse) {
        super(HttpStatus.BAD_REQUEST, simpleResponse);
    }
}
