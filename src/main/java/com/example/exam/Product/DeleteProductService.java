package com.example.exam.Product;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 

import com.example.exam.Command;
import com.example.exam.Exceptions.ProductNotFoundException;

@Service
public class DeleteProductService implements Command<String, Void> {
    private final ProductRepository productRepository;

    DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }

        productRepository.delete(productOptional.get());

        return ResponseEntity.ok().build();
    }
}
