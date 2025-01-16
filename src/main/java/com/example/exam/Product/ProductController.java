package com.example.exam.Product;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepository;

    private final DeleteProductService deleteProductService;

    public ProductController(ProductRepository productRepository, DeleteProductService deleteProductService) {
        this.productRepository = productRepository;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = this.productRepository.findAll();

        return ResponseEntity.ok(products);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductService.execute(id);
    }
}