package com.example.productmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.productmanagement.entity.Product;

public interface ProductService {
    
    List<Product> getAllProducts();
    
    List<Product> getAllProducts(Sort sort);
    
    Optional<Product> getProductById(Long id);
    
    Product saveProduct(Product product);
    
    void deleteProduct(Long id);
    
    Page<Product> searchProducts(String keyword, Pageable pageable);
    
    Page<Product> searchProducts(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    
    List<Product> getProductsByCategory(String category);
    
    List<String> getAllCategories();
    
    long countByCategory(String category);
    
    BigDecimal calculateTotalValue();
    
    BigDecimal calculateAveragePrice();
    
    List<Product> findLowStockProducts(int threshold);
    
    List<Product> getRecentProducts(int count);
}
