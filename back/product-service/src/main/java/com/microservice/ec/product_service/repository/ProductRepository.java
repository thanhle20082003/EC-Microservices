package com.microservice.ec.product_service.repository;

import com.microservice.ec.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySkuCode(String skuCode);

    void deleteBySkuCode(String skuCode);

    boolean existsBySkuCode(String skuCode);
}
