package com.microservice.ec.product_service.dto.response;

import com.microservice.ec.product_service.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    UUID id;

    String name;

    String slug;

    Set<Product> products;
}
