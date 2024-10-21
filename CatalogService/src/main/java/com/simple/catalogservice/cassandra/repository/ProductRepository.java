package com.simple.catalogservice.cassandra.repository;

import com.simple.catalogservice.cassandra.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
