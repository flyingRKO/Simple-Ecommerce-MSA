package com.simple.catalogservice.dto;

import java.util.List;

public record RegisterProductRequest(
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
) {
}
