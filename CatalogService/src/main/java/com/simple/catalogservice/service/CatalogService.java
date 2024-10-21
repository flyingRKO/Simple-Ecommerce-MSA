package com.simple.catalogservice.service;

import com.simple.catalogservice.cassandra.entity.Product;
import com.simple.catalogservice.cassandra.repository.ProductRepository;
import com.simple.catalogservice.dto.ProductDto;
import com.simple.catalogservice.exception.ErrorCode;
import com.simple.catalogservice.exception.SimpleEcommerceException;
import com.simple.catalogservice.mysql.entity.SellerProduct;
import com.simple.catalogservice.mysql.repository.SellerProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final SellerProductRepository sellerProductRepository;
    private final ProductRepository productRepository;

    public ProductDto registerProduct(
            Long sellerId,
            String name,
            String description,
            Long price,
            Long stockCount,
            List<String> tags
    ){
        SellerProduct sellerProduct = SellerProduct.of(sellerId);
        sellerProductRepository.save(sellerProduct);

        Product product = Product.of(
                sellerProduct.getId(),
                sellerId,
                name,
                description,
                price,
                stockCount,
                tags
        );
        productRepository.save(product);
        return ProductDto.toDto(product);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        sellerProductRepository.deleteById(productId);
    }

    public List<ProductDto> getProductsBySellerId(Long sellerId){
        List<SellerProduct> sellerProducts = sellerProductRepository.findBySellerId(sellerId);
        List<Product> products = new ArrayList<>();

        for(SellerProduct item : sellerProducts){
            Optional<Product> product = productRepository.findById(item.getId());
            product.ifPresent(products::add);
        }
        return products.stream()
                .map(ProductDto::toDto)
                .toList();
    }

    public ProductDto getProduct(UUID productId) {
        return productRepository.findById(productId)
                .map(ProductDto::toDto)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public ProductDto decreaseStockCount(UUID productId, Long decreaseCount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new SimpleEcommerceException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setStockCount(product.getStockCount() - decreaseCount);
        productRepository.save(product);
        return ProductDto.toDto(product);
    }
}
