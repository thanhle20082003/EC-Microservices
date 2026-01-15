package com.microservice.ec.inventory_service.mapper;

import com.microservice.ec.inventory_service.dto.response.InventoryResponse;
import com.microservice.ec.inventory_service.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    public InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .skuId(inventory.getSkuId())
                .quantity(inventory.getQuantity())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
