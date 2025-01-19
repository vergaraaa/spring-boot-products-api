package com.example.exam.Product;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exam.Query;
import com.example.exam.Exceptions.ProductNotFoundException;

@Service
public class GetProductService implements Query<String, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return ResponseEntity.ok(new ProductDTO(productOptional.get()));
    }
    
}
