package com.example.productapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // 1️⃣
@Table(name = "products") // 2️⃣
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id // 3️⃣
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4️⃣
    private Long id;

    @Column(nullable = false) // 5️⃣
    private String name;

    private String description;

    @Column(nullable = false) // 5️⃣
    private BigDecimal price;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}