package com.example.exam.Product.ProductValidation;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProfanityFilterAPIResponse {
    private String original;
    private String censored;
    private boolean has_profanity;
}
