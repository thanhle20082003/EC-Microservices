package com.microservice.ec.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, unique = true)
    String skuCode;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    BigDecimal price;

    String imageUrl;

    @Column(columnDefinition = "nvarchar(255)")
    String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
