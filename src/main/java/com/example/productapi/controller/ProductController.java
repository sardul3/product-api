package com.example.productapi.controller;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.mapper.ProductModelAssembler;
import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductModelAssembler productModelAssembler;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProducts() {
        log.info("Received request to get all products");
        List<Product> products = productService.getAllProducts();
        CollectionModel<EntityModel<Product>> collectionModel = productModelAssembler.toCollectionModel(products);
        log.info("Returning {} products", products.size());
        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable("id") Long id) {
        log.info("Received request to get product with id: {}", id);
        Product product = productService.getProductById(id);
        EntityModel<Product> productModel = productModelAssembler.toModel(product);
        log.info("Returning product with id: {}", id);
        return ResponseEntity.ok(productModel);
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<EntityModel<Product>> createProduct(@Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to create new product: {}", productDTO.getName());
        Product product = productMapper.productDtoToProduct(productDTO);
        Product createdProduct = productService.createProduct(product);
        EntityModel<Product> productModel = productModelAssembler.toModel(createdProduct);
        log.info("Created new product with id: {}", createdProduct.getId());
        return ResponseEntity
                .created(productModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDTO) {
        log.info("Received request to update product with id: {}", id);
        Product product = productMapper.productDtoToProduct(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        EntityModel<Product> productModel = productModelAssembler.toModel(updatedProduct);
        log.info("Updated product with id: {}", id);
        return ResponseEntity.ok(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        log.info("Received request to delete product with id: {}", id);
        productService.deleteProduct(id);
        log.info("Deleted product with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}