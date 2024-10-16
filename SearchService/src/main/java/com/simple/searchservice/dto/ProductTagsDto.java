package com.simple.searchservice.dto;

import java.util.List;

public record ProductTagsDto(
        Long productId,
        List<String> tags
) {
}
