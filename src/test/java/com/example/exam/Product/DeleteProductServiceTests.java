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
import com.example.exam.Exceptions.ProductNotFoundException;

@SpringBootTest(classes = ExamApplicationTests.class)
public class DeleteProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    private DeleteProductService deleteProductService;

    @BeforeEach
    void setup() {
        deleteProductService = new DeleteProductService(productRepository);
    }

    @Test
    void deleteProductService_returnsSuccess() {
        String id = "1";
        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ResponseEntity<Void> responseEntity = deleteProductService.execute(id);

        verify(productRepository).delete(product);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteProductService_throwsProductNotFoundException() {
        String id = "1";
        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
            ProductNotFoundException.class, () -> deleteProductService.execute(id)
        );

        assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());
    }
}
