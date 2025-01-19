package com.example.exam.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exam.ExamApplicationTests;
import com.example.exam.Category.Category;
import com.example.exam.Exceptions.ErrorMessage;
import com.example.exam.Exceptions.ProductNotFoundException;

@SpringBootTest(classes = ExamApplicationTests.class)
public class GetProductServiceTests {
    
    @Mock 
    ProductRepository productRepository;

    private GetProductService getProductService;


    @BeforeEach
    void setup() {
        this.getProductService = new GetProductService(productRepository);
    }

    @Test
    void getProductById_returnsSuccess() {
        String id = "1";
        Product product = new Product();
        product.setId(id);
        product.setCategory(new Category("Electronics"));
        
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        
        ResponseEntity<ProductDTO> responseEntity = getProductService.execute(id);

        verify(productRepository).findById(id);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), new ProductDTO(product));
    }
    
    @Test 
    void getProductById_throwsProductNotFounException() {
        String id = "1";
        Product product = new Product();
        product.setId(id);
        product.setCategory(new Category("Electronics"));
        
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
            ProductNotFoundException.class, 
            () -> getProductService.execute(id)
        );

        assertEquals(ErrorMessage.PRODUCT_NOT_FOUND.getMessage(), exception.getSimpleResponse().getMessage());
    }
}
