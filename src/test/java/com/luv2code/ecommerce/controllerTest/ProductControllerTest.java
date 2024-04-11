package com.luv2code.ecommerce.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.ecommerce.controller.ProductController;
import com.luv2code.ecommerce.dto.ProductRequest;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import static com.luv2code.ecommerce.controllerTest.UserControllerTest.asJsonString;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
//    @Test
//    void testGetAllProducts() throws Exception {
//        // Mock data
//        Product product1 = new Product();
//        product1.setId(1L);
//        product1.setName("Product 1");
//        product1.setUnitPrice(BigDecimal.valueOf(20.99));
//
//        Product product2 = new Product();
//        product2.setId(2L);
//        product2.setName("Product 2");
//        product2.setUnitPrice(BigDecimal.valueOf(15.49));
//
//        List<Product> productList = Arrays.asList(product1, product2);
//
//        when(productService.getAllProducts()).thenReturn(productList);
//
//        // Perform the GET request
//        mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Product 1"))
//                .andExpect(jsonPath("$[0].unitPrice").value(20.99))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].name").value("Product 2"))
//                .andExpect(jsonPath("$[1].unitPrice").value(15.49));
//
//        // Verify that the productService.getAllProducts() method was called once
//        verify(productService, times(1)).getAllProducts();
//    }

    @Test
    void testAddProduct() throws Exception {
        // Mock data
        ProductRequest request = new ProductRequest();
        request.setProductName("New Product");
        request.setCategoryId(1L);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("New Product");

        when(productService.addProduct(any(ProductRequest.class))).thenReturn(savedProduct);

        // Perform the POST request
        mockMvc.perform(post("/api/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Product"));

        // Verify that the productService.addProduct() method was called once
        verify(productService, times(1)).addProduct(any(ProductRequest.class));
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    }
