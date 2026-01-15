package com.microservice.ec.inventory_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryRequest {

    UUID id;

    UUID skuId;

    Long quantity;
}
