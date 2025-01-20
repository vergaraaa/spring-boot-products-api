package com.example.exam.Product;

import lombok.Getter;

@Getter
public class GetProductsQuery {
    private Region region;
    private String category;
    private String nameOrDescription;
    private ProductOrderBy productOrderBy;


    public GetProductsQuery(Region region, String category, String nameOrDescription, ProductOrderBy productOrderBy) {
        this.region = region;
        this.category = category;
        this.nameOrDescription = nameOrDescription;
        this.productOrderBy = productOrderBy;
    }
    
}
