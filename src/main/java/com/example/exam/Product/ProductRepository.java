package com.example.exam.Product;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(
        "SELECT p FROM Product p WHERE " + 
        "(:nameOrDescription is null or p.name like %:nameOrDescription% or p.description like %:nameOrDescription%) AND " +
        "(p.region = :region) AND " + 
        "(:category is null or p.category.value = :category)"
    )
    List<Product> findByNameOrDescriptionAndRegionAndCategory(
        String nameOrDescription,
        Region region,
        String category,
        Sort sort
    );
}
