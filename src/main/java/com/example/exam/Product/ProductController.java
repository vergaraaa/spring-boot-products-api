package com.example.exam.Product;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("products")
public class ProductController {
    private final GetProductService getProductService;
    private final DeleteProductService deleteProductService;

    public ProductController(
        GetProductService getProductService, 
        DeleteProductService deleteProductService
    ) {
        this.getProductService = getProductService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return this.getProductService.execute(id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductService.execute(id);
    }
}