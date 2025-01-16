package com.example.exam.Category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exam.Query;

@Service
public class GetCategoriesService implements Query<Void, List<String>> {
    private final CategoryRepository categoryRepository;

    public GetCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<String>> execute(Void input) {
        return ResponseEntity.ok(
            categoryRepository
                .findAll()
                .stream()
                .map(Category::getValue)
                .collect(Collectors.toList()
                )
            );
    }
    
}
