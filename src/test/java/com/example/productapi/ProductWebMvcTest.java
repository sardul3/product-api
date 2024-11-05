package com.example.productapi;

import com.example.productapi.controller.ProductController;
import com.example.productapi.dto.ProductDto;
import com.example.productapi.exception.DuplicateProductException;
import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.mapper.ProductModelAssembler;
import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    WebMvcTest is a slice where @SpringBootTest loads the entire app context,
    this annotation only loads Controllers, and any other appropriate beans such as filters
 */
@WebMvcTest(controllers = {ProductController.class})
/*
    @Import(ProductService.class)
    -- Use this to directly import a bean instead of mocking it
 */
class ProductWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductMapper productMapper;

    @MockBean
    ProductModelAssembler productModelAssembler;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deleteTestWithProductFoundFromService() throws Exception {

        doNothing().when(productService).deleteProduct(anyLong());

        mockMvc.perform(
                delete("/api/products/1")
        ).andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void deleteTestWithProductNotFoundFromService() throws Exception {
        // For void methods, place doThrow first
        doThrow(ProductNotFoundException.class).when(productService).deleteProduct(anyLong());

        mockMvc.perform(
                delete("/api/products/1")
        ).andExpect(status().isNotFound());

        verify(productService).deleteProduct(1L);
    }
}
