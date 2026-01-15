package com.microservice.ec.inventory_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryResponse {

    UUID id;

    UUID skuId;

    Long quantity;

    Boolean isInStock;
}
