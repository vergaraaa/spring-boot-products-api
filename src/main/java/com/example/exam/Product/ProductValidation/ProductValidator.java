package com.example.exam.Product.ProductValidation;

import java.util.List;

import com.example.exam.Category.Category;
import com.example.exam.Exceptions.ErrorMessage;
import com.example.exam.Exceptions.ProductNotValidException;
import com.example.exam.Exceptions.SimpleResponse;
import com.example.exam.Product.CreateProductDTO;
import com.example.exam.Product.Product;
import com.example.exam.Product.Region;

import io.micrometer.common.util.StringUtils;

public class ProductValidator {
    public static Product execute(CreateProductDTO createProductDTO, List<Category> availableCategories) {
        if(nameIsEmpty(createProductDTO.getName())) {
            throw new ProductNotValidException(new SimpleResponse(ErrorMessage.PRODUCT_NAME_CANNOT_BE_EMPTY.getMessage()));
        }
        
        if(priceIsNegative(createProductDTO.getPrice())) {
            throw new ProductNotValidException(new SimpleResponse(ErrorMessage.PRODUCT_PRICE_CANNOT_BE_NEGATIVE.getMessage()));
        }
        
        if(categoryNotAvailable(createProductDTO.getCategory(), availableCategories)) {
            throw new ProductNotValidException(new SimpleResponse(ErrorMessage.PRODUCT_CATEGORY_NOT_AVAILABLE.getMessage()));   
        }
        
        if(regionNotAvailable(createProductDTO.getRegion())) {
            throw new ProductNotValidException(new SimpleResponse(ErrorMessage.PRODUCT_REGION_NOT_AVAILABLE.getMessage()));
        }
                                                        
        // profanity validator
        if(ProfanityValidator.containsProfanity(createProductDTO.getName(), createProductDTO.getDescription())) {
            throw new ProductNotValidException(new SimpleResponse(ErrorMessage.PRODUCT_CONTAINS_PROFANITY.getMessage()));
        }
        
        return new Product(createProductDTO);
    }
                                                                
    private static boolean nameIsEmpty(String name) {
        return StringUtils.isEmpty(name);
    }
    
    private static boolean priceIsNegative(Double price) {
        return price != null && price < 0.0;
    }
    
    private static boolean categoryNotAvailable(String category, List<Category> availableCategories) {
        return !availableCategories.contains(new Category(category));
    }

    private static boolean regionNotAvailable(String region) {
        for(Region availableRegion : Region.values()) {
            if(availableRegion.name().equals(region)) {
                return false;
            }
        }

        return true;
    }
}