package com.simple.catalogservice.mysql.repository;

import com.simple.catalogservice.mysql.entity.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SellerProductRepository extends JpaRepository<SellerProduct, UUID> {
    List<SellerProduct> findBySellerId(Long sellerId);
}
