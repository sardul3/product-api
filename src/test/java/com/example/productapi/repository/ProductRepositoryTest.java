package com.example.productapi.repository;

import com.example.productapi.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Long savedProductId;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        Product product = productRepository.save(new Product("P001", "P002", new BigDecimal("003")));
        savedProductId = product.getId();
    }

    @Test
    void findAllTest() {
        assertThat(productRepository.findAll()).hasSize(1)
                .first()
                .extracting(Product::getDescription)
                .isEqualTo("P002");
    }

    @Test
    void findByIdTest() {
        assertThat(productRepository.findById(savedProductId)).isNotNull()
                .get()
                .extracting(Product::getName)
                .isEqualTo("P001");
    }

    @Test
    void findByNameTest() {
        assertThat(productRepository.existsByName("P001")).isTrue();
    }

}