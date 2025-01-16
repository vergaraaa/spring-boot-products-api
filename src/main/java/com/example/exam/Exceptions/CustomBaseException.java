package com.example.exam.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomBaseException extends RuntimeException {
    private HttpStatus status;
    private SimpleResponse simpleResponse;
}
