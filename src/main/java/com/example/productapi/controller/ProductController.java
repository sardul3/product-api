package com.example.productapi.controller;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Received request to get all products");
        List<ProductDto> products = productService.getAllProducts().stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
        log.info("Returning {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        log.info("Received request to get product with id: {}", id);
        Product product = productService.getProductById(id);
        ProductDto productDto = productMapper.productToProductDto(product);
        log.info("Returning product with id: {}", id);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to create new product: {}", productDTO.getName());
        Product product = productMapper.productDtoToProduct(productDTO);
        Product createdProduct = productService.createProduct(product);
        log.info("Created new product with id: {}", createdProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to update product with id: {}", id);
        Product product = productMapper.productDtoToProduct(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        ProductDto updatedProductDto = productMapper.productToProductDto(updatedProduct);
        log.info("Updated product with id: {}", id);
        return ResponseEntity.ok(updatedProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        log.info("Received request to delete product with id: {}", id);
        productService.deleteProduct(id);
        log.info("Deleted product with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}