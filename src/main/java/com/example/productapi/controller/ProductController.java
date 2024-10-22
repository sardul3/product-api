package com.example.productapi.controller;

import com.example.productapi.dto.ProductDto;
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

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Received request to get all products");
        List<ProductDto> products = productService.getAllProducts().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.info("Returning {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        log.info("Received request to get product with id: {}", id);
        Product product = productService.getProductById(id);
        ProductDto productDto = convertToDTO(product);
        log.info("Returning product with id: {}", id);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to create new product: {}", productDTO.getName());
        Product product = convertToEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        log.info("Created new product with id: {}", createdProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to update product with id: {}", id);
        Product product = convertToEntity(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        ProductDto updatedProductDto = convertToDTO(updatedProduct);
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

    private ProductDto convertToDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }

    private Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }
}