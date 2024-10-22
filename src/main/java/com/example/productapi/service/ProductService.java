package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;


import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.exception.DuplicateProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        log.debug("Fetching product with id: {}", id);
        try {
            return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product not found with id: {}", id);
                    return new ProductNotFoundException(id);
                });
        } catch (Exception e) {
            log.error("Unexpected error when fetching product with id: {}", id, e);
            throw e;
        }
    }

    public Product createProduct(Product product) {
        log.info("Creating new product with name: {}", product.getName());
        if (productRepository.existsByName(product.getName())) {
            log.warn("Attempt to create duplicate product with name: {}", product.getName());
            throw new DuplicateProductException(product.getName());
        }
        Product savedProduct = productRepository.save(product);
        log.info("Created new product with id: {}", savedProduct.getId());
        return savedProduct;
    }

    public Product updateProduct(Long id, Product productDetails) {
        log.info("Updating product with id: {}", id);
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("Attempt to update non-existent product with id: {}", id);
                return new ProductNotFoundException(id);
            });
        
        if (!existingProduct.getName().equals(productDetails.getName()) &&
            productRepository.existsByName(productDetails.getName())) {
            log.warn("Attempt to update product with duplicate name: {}", productDetails.getName());
            throw new DuplicateProductException(productDetails.getName());
        }
        
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Updated product with id: {}", updatedProduct.getId());
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        if (!productRepository.existsById(id)) {
            log.warn("Attempt to delete non-existent product with id: {}", id);
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        log.info("Deleted product with id: {}", id);
    }
}