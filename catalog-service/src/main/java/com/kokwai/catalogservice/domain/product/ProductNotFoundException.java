package com.kokwai.catalogservice.domain.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException exForCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}
