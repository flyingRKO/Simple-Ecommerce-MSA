package com.simple.searchservice.controller;

import com.simple.searchservice.dto.ProductTagsDto;
import com.simple.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/search/addTagCache")
    public void addTagCache(@RequestBody ProductTagsDto request){
        searchService.addTagCache(request.productId(), request.tags());
    }

    @PostMapping("/search/removeTagCache")
    public void removeTagCache(@RequestBody ProductTagsDto request){
        searchService.removeTagCache(request.productId(), request.tags());
    }

    @GetMapping("/search/tags/{tag}/productIds")
    public List<Long> getTagProductIds(@PathVariable String tag){
        return searchService.getProductIdsByTag(tag);
    }
}
