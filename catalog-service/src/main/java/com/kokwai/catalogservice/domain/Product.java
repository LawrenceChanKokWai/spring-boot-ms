package com.kokwai.catalogservice.domain;

import java.math.BigDecimal;

public record Product(String code, String name, String description, String image_url, BigDecimal price) {}
