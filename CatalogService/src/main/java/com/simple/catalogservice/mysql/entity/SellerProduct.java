package com.simple.catalogservice.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Entity
public class SellerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long sellerId;

    private SellerProduct(Long sellerId) {
        this.sellerId = sellerId;
    }

    public static SellerProduct of(Long sellerId){
        return new SellerProduct(sellerId);
    }
}
