package com.simple.catalogservice.cassandra.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Getter
@Table
public class Product {

    @PrimaryKey
    private UUID id;
    @Column
    private Long sellerId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Long price;
    @Column
    @Setter
    private Long stockCount;
    @Column
    private List<String> tags;

    private Product(UUID id, Long sellerId, String name, String description, Long price, Long stockCount, List<String> tags) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
        this.tags = tags;
    }

    public static Product of(UUID id, Long sellerId, String name, String description, Long price, Long stockCount, List<String> tags) {
        return new Product(id, sellerId, name, description, price, stockCount, tags);
    }
}
