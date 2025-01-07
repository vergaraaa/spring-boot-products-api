package com.example.exam.Product;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ProductController {
    @GetMapping("/sanitycheck")
    public String sanityCheck() {
        return "Sanity check";
    }
    
}