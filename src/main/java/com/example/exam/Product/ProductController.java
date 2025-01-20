package com.example.exam.Product;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("products")
public class ProductController {
    private final GetProductService getProductService;
    private final GetProductsService getProductsService;
    private final DeleteProductService deleteProductService;

    public ProductController(
        GetProductService getProductService, 
        GetProductsService getProductsService,
        DeleteProductService deleteProductService
    ) {
        this.getProductService = getProductService;
        this.getProductsService = getProductsService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return this.getProductService.execute(id);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(
        @RequestHeader(defaultValue = "US") String region,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String nameOrDescription,
        @RequestParam(required = false) String orderBy
    ) {
        return getProductsService.execute(new GetProductsQuery(
            Region.valueOf(region), 
            category, 
            nameOrDescription, 
            ProductOrderBy.fromValue(orderBy)
        ));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductService.execute(id);
    }
}