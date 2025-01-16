package com.example.exam;

import org.springframework.http.ResponseEntity;

public interface Query<I,O> {
    ResponseEntity<O> execute(I input);
}
