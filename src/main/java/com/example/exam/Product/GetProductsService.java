package com.example.exam.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exam.Query;

@Service
public class GetProductsService implements Query<GetProductsQuery, List<ProductDTO>> {

    private final ProductRepository productRepository;


    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQuery query) {
        Sort productSort = defineSort(query.getProductOrderBy());
                
        List<Product> products = productRepository.findByNameOrDescriptionAndRegionAndCategory(
            query.getNameOrDescription(), 
            query.getRegion(), 
            query.getCategory(), 
            productSort
        );


        
        return ResponseEntity.ok(products.stream().map(ProductDTO::new).collect(Collectors.toList()));
    }
            
    public Sort defineSort(ProductOrderBy productOrderBy) {
        if(productOrderBy == null) {
            return Sort.unsorted();
        }

        ProductOrderBy sortBy = ProductOrderBy.valueOf(productOrderBy.getValue());

        return Sort.by(String.valueOf(sortBy));
    }
    
}
