package com.example.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.exam.Category.Category;
import com.example.exam.Category.CategoryRepository;
import com.example.exam.Category.GetCategoriesService;

@SpringBootTest(classes = ExamApplicationTests.class)
public class GetCategoriesServiceTests {
    @Mock
    private CategoryRepository categoryRepository;

    private GetCategoriesService getCategoriesService;

    @BeforeEach
    void setup() {
        getCategoriesService = new GetCategoriesService(categoryRepository);
    }

    @Test
    void getCategoriesService_returnsList() {
        List<Category> categoryList = Arrays.asList(
            new Category("Electronics"),
            new Category("Bathroom"),
            new Category("Automobile")
        );

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<String> expected = Arrays.asList("Electronics","Bathroom","Automobile");

        ResponseEntity<List<String>> actual = getCategoriesService.execute(null);

        assertEquals(ResponseEntity.ok(expected), actual);
    }
    
    @Test
    void getCategoriesService_returnsEmptyList() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<String>> actual = getCategoriesService.execute(null);

        assertEquals(ResponseEntity.ok(Collections.emptyList()), actual);
    }
}
