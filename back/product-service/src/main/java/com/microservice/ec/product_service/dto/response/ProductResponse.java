package com.microservice.ec.product_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    UUID id;

    String skuCode;

    String name;

    String description;

    BigDecimal price;

    String imageUrl;

    String categoryId;
}
