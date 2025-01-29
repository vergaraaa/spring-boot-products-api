package com.example.exam.Product;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exam.Command;
import com.example.exam.Category.CategoryRepository;
import com.example.exam.Product.ProductValidation.ProductValidator;

@Service
public class CreateProductService implements Command<CreateProductDTO, ProductDTO> {

    private final Logger logger = LoggerFactory.getLogger(CreateProductService.class);

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    CreateProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(CreateProductDTO input) {
        logger.info("Create product service executing with input: {}", input);

        Product validatedProduct = ProductValidator.execute(input, categoryRepository.findAll());
        
        productRepository.save(validatedProduct);

        return ResponseEntity.ok(new ProductDTO(validatedProduct));
    }
    
}
