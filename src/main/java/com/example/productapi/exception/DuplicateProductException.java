package com.example.productapi.exception;


public class DuplicateProductException extends RuntimeException {
    private final String productName;

    public DuplicateProductException(String productName) {
        super("Product with name '" + productName + "' already exists");
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
