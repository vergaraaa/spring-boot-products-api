package com.example.exam.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.example.exam.ExamApplicationTests;
import com.example.exam.Category.Category;

@SpringBootTest(classes = ExamApplicationTests.class)
public class GetProductsServiceTests {
    @Mock
    private ProductRepository productRepository;

    private GetProductsService getProductsService;

    @BeforeEach()
    void setup() {
        this.getProductsService = new GetProductsService(productRepository);
    }

    @Test
    void testGetProductsService_returnsEmptyList() {
        when(productRepository
            .findByNameOrDescriptionAndRegionAndCategory(null, null, null, null))
            .thenReturn(Collections.emptyList());
            
            ResponseEntity<List<ProductDTO>> responseEntity = getProductsService.execute(new GetProductsQuery(null, null, null, null));
            
            assertEquals(responseEntity.getBody(), Collections.emptyList());
        }
        
    @Test
    void testGetProductsService_returnsList() {
        when(productRepository
            .findByNameOrDescriptionAndRegionAndCategory(any(), any(), any(), any()))
            .thenReturn(getProducts());
        
        ResponseEntity<List<ProductDTO>> responseEntity = getProductsService.execute(new GetProductsQuery(null, null, null, null));

        List<ProductDTO> actualList = responseEntity.getBody();

        assertEquals(2, actualList.size());
    }

    @Test
    void testDefineSort_returnsUnsorted() {
        Sort sort = getProductsService.defineSort(null);

        assertEquals(Sort.unsorted(), sort);
    }

    @Test
    void testDefineSort_returnsSorted() {
        Sort sort = getProductsService.defineSort(ProductOrderBy.name);

        assertEquals(Sort.by(Sort.Direction.ASC, "name"), sort);
    }
            
    private List<Product> getProducts() {
        Product product1 = new Product();
        product1.setId("1");
        product1.setCategory(new Category("Electronics"));

        Product product2 = new Product();
        product2.setId("2");
        product2.setCategory(new Category("Electronics"));

        return Arrays.asList(product1, product2);
    }
}
