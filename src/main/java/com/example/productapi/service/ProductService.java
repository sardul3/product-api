package com.example.productapi.service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;


import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.exception.DuplicateProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        logger.info("Attempting to fetch product with id: {}", id);
        try {
            return productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Product not found with id: {}", id);
                    return new ProductNotFoundException(id);
                });
        } catch (Exception e) {
            logger.error("Unexpected error when fetching product with id: {}", id, e);
            throw e;
        }
    }

    public Product createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new DuplicateProductException(product.getName());
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        
        if (!existingProduct.getName().equals(productDetails.getName()) &&
            productRepository.existsByName(productDetails.getName())) {
            throw new DuplicateProductException(productDetails.getName());
        }
        
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}