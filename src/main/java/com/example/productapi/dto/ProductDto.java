package com.example.productapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Getter
@Setter
@JacksonXmlRootElement(localName = "ProductDetails")
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Positive(message = "Price must be positive")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    private BigDecimal price;
}
