package com.kokwai.catalogservice.web.controller;

import com.kokwai.catalogservice.domain.product.PageResponse;
import com.kokwai.catalogservice.domain.product.Product;
import com.kokwai.catalogservice.domain.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kokwai.catalogservice.domain.product.ProductNotFoundException.exForCode;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PageResponse<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int page) {
        return productService.getProducts(page);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProduct(@PathVariable String code) {
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> exForCode(code));
    }

}
