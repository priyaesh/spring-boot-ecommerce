package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.ProductCategoryRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dto.ProductRequest;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


//    public Product addProduct(String productName, Long categoryId) {
//        System.out.println(productName + "testing here");
//
//        ProductCategory category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
//
//        Product product = new Product();
//        product.setName(productName);
//        product.setCategory(category);
//        System.out.println(productName);
//        System.out.println(category);
//
//        return productRepository.save(product);
//    }

    public Product addProduct(ProductRequest request) {
        System.out.println(  " testing here");

        ProductCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = new Product();
        product.setName(request.getProductName());
        product.setCategory(category);
        System.out.println(request.getProductName());
        System.out.println(category);

        return productRepository.save(product);
    }

}
