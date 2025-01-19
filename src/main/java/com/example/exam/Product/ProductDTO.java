package com.example.exam.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // overwrites equals methos & compares contents of objects instead of memory address
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private String category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.category = product.getCategory().getValue();
    }
}
