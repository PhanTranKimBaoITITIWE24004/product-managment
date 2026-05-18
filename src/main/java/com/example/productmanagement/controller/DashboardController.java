package com.example.productmanagement.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.ProductService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ProductService productService;

    public DashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        List<String> categories = productService.getAllCategories();
        Map<String, Long> productsByCategory = categories.stream()
                .collect(Collectors.toMap(
                        category -> category,
                        category -> productService.countByCategory(category)));

        List<Product> recentProducts = productService.getRecentProducts(5);
        List<Product> lowStockProducts = productService.findLowStockProducts(10);

        BigDecimal totalValue = Objects.requireNonNullElse(productService.calculateTotalValue(), BigDecimal.ZERO);
        BigDecimal averagePrice = Objects.requireNonNullElse(productService.calculateAveragePrice(), BigDecimal.ZERO);

        model.addAttribute("totalProducts", productService.getAllProducts().size());
        model.addAttribute("productsByCategory", productsByCategory);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("lowStockProducts", lowStockProducts);
        model.addAttribute("recentProducts", recentProducts);

        return "dashboard";
    }
}
