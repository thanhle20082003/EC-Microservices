package com.microservice.ec.inventory_service.service;

import com.microservice.ec.inventory_service.client.ProductClient;
import com.microservice.ec.inventory_service.dto.request.InventoryRequest;
import com.microservice.ec.inventory_service.dto.response.InventoryResponse;
import com.microservice.ec.inventory_service.entity.Inventory;
import com.microservice.ec.inventory_service.exception.ArchitectureException;
import com.microservice.ec.inventory_service.exception.ErrorCode;
import com.microservice.ec.inventory_service.mapper.InventoryMapper;
import com.microservice.ec.inventory_service.repository.InventoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryService {

    InventoryRepository inventoryRepository;
    InventoryMapper inventoryMapper;
    ProductClient productClient;

    @Transactional(readOnly = true)
    @SneakyThrows // bỏ throws Exception
    public List<InventoryResponse> isInStock(List<UUID> skuId) {
        return inventoryRepository.findBySkuIdIn(skuId).stream()
                .map(inventoryMapper::toInventoryResponse).toList();
    }

    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {

        var productResponse = productClient.isProductExists(inventoryRequest.getSkuId()).getResult();
        if (!productResponse) {
            throw new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Product");
        }

        return inventoryMapper.toInventoryResponse(
                inventoryRepository.save(
                        Inventory.builder()
                                .skuId(inventoryRequest.getSkuId())
                                .quantity(inventoryRequest.getQuantity())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build()
                )
        );
    }

    public InventoryResponse updateInventory(InventoryRequest inventoryRequest) {

        Inventory existingInventory = inventoryRepository.findById(inventoryRequest.getId())
                .orElseThrow(() -> new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Inventory"));
        existingInventory.setQuantity(inventoryRequest.getQuantity());
        existingInventory.setUpdatedAt(LocalDateTime.now());

        return inventoryMapper.toInventoryResponse(inventoryRepository.save(existingInventory));
    }
}
