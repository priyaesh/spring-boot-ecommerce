package com.luv2code.ecommerce.serviceTest;

import com.luv2code.ecommerce.dao.ProductCategoryRepository;
import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dto.ProductRequest;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        // Mock data
        ProductRequest request = new ProductRequest();
        request.setProductName("Test1 Product");
        request.setCategoryId(1L);

        ProductCategory category = new ProductCategory();
        category.setId(1L);
        category.setCategoryName("Test Category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Test Product");
        savedProduct.setCategory(category);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Perform the operation
        Product addedProduct = productService.addProduct(request);

        // Verify the result
        assertEquals("Test Product", addedProduct.getName());
        assertEquals(category, addedProduct.getCategory());

        // Verify that repository methods were called
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
