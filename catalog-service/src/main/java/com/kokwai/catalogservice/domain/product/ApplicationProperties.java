package com.kokwai.catalogservice.domain.product;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "catalogservice")
public record ApplicationProperties(@DefaultValue("10") @Min(1) int pageSize) {}
