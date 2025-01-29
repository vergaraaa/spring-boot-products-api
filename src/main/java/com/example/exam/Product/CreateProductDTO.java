package com.example.exam.Product;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateProductDTO {
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private String category;
    private String region;
}
