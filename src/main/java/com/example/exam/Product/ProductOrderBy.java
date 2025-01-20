package com.example.exam.Product;

public enum ProductOrderBy {
    name("name"),
    price("price");

    private final String value;

    ProductOrderBy(String value) {
        this.value = value;
    }

    public static ProductOrderBy fromValue(String value) {
        for(ProductOrderBy sortBy: ProductOrderBy.values()) {
            if(sortBy.value.equals(value)) {
                return sortBy;
            }
        }

        return null;
    }

    public String getValue() {
        return this.value;
    }
}
