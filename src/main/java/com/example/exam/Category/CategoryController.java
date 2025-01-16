package com.example.exam.Category;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("categories")
public class CategoryController {
    private final GetCategoriesService getCategoriesService;

    public CategoryController(GetCategoriesService getCategoriesService) {
        this.getCategoriesService = getCategoriesService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCategories() {
        return this.getCategoriesService.execute(null);
    }
}