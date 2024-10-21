package com.simple.catalogservice.dto;

import com.simple.catalogservice.cassandra.entity.Product;

import java.util.List;
import java.util.UUID;

public record ProductDto(
        UUID id,
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
) {
    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getSellerId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockCount(),
                product.getTags()
        );
    }
}
