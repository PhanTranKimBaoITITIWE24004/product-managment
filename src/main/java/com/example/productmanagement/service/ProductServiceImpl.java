package com.example.productmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword == null ? "" : keyword, pageable);
    }

    @Override
    public Page<Product> searchProducts(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        String nameParam = (name != null && !name.isBlank()) ? name : null;
        String categoryParam = (category != null && !category.isBlank()) ? category : null;
        List<Product> results = productRepository.advancedSearch(nameParam, categoryParam, minPrice, maxPrice);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), results.size());
        List<Product> pageContent = start <= end ? results.subList(start, end) : List.of();
        return new PageImpl<>(pageContent, pageable, results.size());
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public long countByCategory(String category) {
        return productRepository.countByCategory(category);
    }

    @Override
    public BigDecimal calculateTotalValue() {
        BigDecimal total = productRepository.calculateTotalValue();
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateAveragePrice() {
        BigDecimal avg = productRepository.calculateAveragePrice();
        return avg != null ? avg : BigDecimal.ZERO;
    }

    @Override
    public List<Product> findLowStockProducts(int threshold) {
        return productRepository.findLowStockProducts(threshold);
    }

    @Override
    public List<Product> getRecentProducts(int count) {
        if (count <= 0) {
            return List.of();
        }
        Pageable pageable = PageRequest.of(0, count, Sort.by("createdAt").descending());
        return productRepository.findAll(pageable).getContent();
    }
}