package com.microservice.ec.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductRequest {

    @NotBlank
    String skuCode;

    @NotBlank
    String name;

    String description;

    @NotNull
    BigDecimal price;

    Long initialStock = 0L;

    String imageUrl;

    UUID categoryId;
}
