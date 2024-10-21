package com.simple.catalogservice.controller;

import com.simple.catalogservice.dto.DecreaseStockCountRequest;
import com.simple.catalogservice.dto.ProductDto;
import com.simple.catalogservice.dto.RegisterProductRequest;
import com.simple.catalogservice.dto.Response;
import com.simple.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @PostMapping("/catalog/products")
    public Response<ProductDto> registerProduct(@RequestBody RegisterProductRequest request){
        ProductDto product = catalogService.registerProduct(
                request.sellerId(),
                request.name(),
                request.description(),
                request.price(),
                request.stockCount(),
                request.tags()
        );
        return Response.success(product);
    }

    @DeleteMapping("/catalog/products/{productId}")
    public Response<Void> deleteProduct(@PathVariable UUID productId){
        catalogService.deleteProduct(productId);
        return Response.success();
    }

    @GetMapping("/catalog/products/{productId}")
    public Response<ProductDto> getProduct(@PathVariable UUID productId){
        ProductDto product = catalogService.getProduct(productId);
        return Response.success(product);
    }

    @GetMapping("/catalog/sellers/{sellerId}/products")
    public Response<List<ProductDto>> getProductsBySellerId(@PathVariable Long sellerId) {
        List<ProductDto> products = catalogService.getProductsBySellerId(sellerId);
        return Response.success(products);
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    public Response<ProductDto> decreaseStockCount(@PathVariable UUID productId, @RequestBody DecreaseStockCountRequest request) {
        ProductDto product = catalogService.decreaseStockCount(productId, request.decreaseCount());
        return Response.success(product);
    }
}
