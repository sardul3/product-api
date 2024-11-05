package com.example.productapi.service;

import com.example.productapi.exception.DuplicateProductException;
import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductByIdWhenProductFoundTest() {
        Long productId = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product("P1", "dP1", BigDecimal.TEN)));
        Product product = productService.getProductById(productId);
        assertNotNull(product);
        assertThat(product.getName()).isEqualTo("P1");
    }

    @Test
    void getProductByIdWhenProductNotFoundTest() {
        Long productId = 1L;
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    void createProductTest() {
        Product product = new Product("P1", "dP1", BigDecimal.TEN);
        when(productRepository.save(any())).thenReturn(product);
        Product createdProduct = productService.createProduct(product);
        assertThat(createdProduct).isNotNull()
                .extracting(Product::getName)
                .isEqualTo("P1");
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void createDuplicateProductTest() {
        Product product = new Product("P1", "dP1", BigDecimal.TEN);
        when(productRepository.existsByName(any())).thenReturn(Boolean.TRUE);
        assertThrows(DuplicateProductException.class, () -> productService.createProduct(product));

        verify(productRepository, times(1)).existsByName(any());
        verify(productRepository, never()).save(any());
    }

    @Test
    void updateProductThatDoesNotExistTest() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1L, any(Product.class)));

        verify(productRepository, never()).save(any());
    }

    @Test
    void updateProductWithNameThatAlreadyExistsTest() {
        when(productRepository.existsByName(any())).thenReturn(Boolean.TRUE);
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product("P1", "dP1", BigDecimal.TEN)));
        assertThrows(DuplicateProductException.class, () -> productService.updateProduct(anyLong(), new Product("P2", "dP1", BigDecimal.TEN)));

        verify(productRepository, never()).save(any());
    }

    @Test
    void updateProductValidUsecase() {
        // Arrange: Set up the existing product and the updated product details
        Long productId = 1L;
        Product existingProduct = new Product("P1", "dP1", BigDecimal.TEN);
        Product updatedProductDetails = new Product("P1", "new Description", BigDecimal.valueOf(15));
        Product savedProduct = new Product("P1", "new Description", BigDecimal.valueOf(15));

        // Mock repository behavior
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(savedProduct);

        // Act: Call the service method to update the product
        Product updatedProduct = productService.updateProduct(productId, updatedProductDetails);

        // Assert: Verify the result and interactions
        assertThat(updatedProduct)
                .isNotNull()
                .extracting(Product::getDescription, Product::getPrice)
                .containsExactly("new Description", BigDecimal.valueOf(15));

        // Verify that findById and save methods were called once
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

}