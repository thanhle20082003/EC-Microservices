package com.microservice.ec.inventory_service.controller;

import com.microservice.ec.inventory_service.client.ProductClient;
import com.microservice.ec.inventory_service.dto.request.InventoryRequest;
import com.microservice.ec.inventory_service.dto.response.InventoryResponse;
import com.microservice.ec.inventory_service.exception.ArchitectureException;
import com.microservice.ec.inventory_service.exception.ErrorCode;
import com.microservice.ec.inventory_service.service.InventoryService;
import com.microservice.ec.inventory_service.dto.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryController {

    InventoryService inventoryService;

    @GetMapping("/is-in-stock")
    public ApiResponse<List<InventoryResponse>> isInStock(@RequestParam List<UUID> skuId) {
        return ApiResponse.<List<InventoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get inventory status successfully")
                .result(inventoryService.isInStock(skuId))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<InventoryResponse> createInventory(@RequestBody InventoryRequest inventoryRequest) {
        return ApiResponse.<InventoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Inventory created successfully")
                .result(inventoryService.createInventory(inventoryRequest))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<InventoryResponse> updateInventory(@RequestBody InventoryRequest inventoryRequest) {
        return ApiResponse.<InventoryResponse>builder()
                .code(HttpStatus.ACCEPTED.value())
                .message("Inventory updated successfully")
                .result(inventoryService.updateInventory(inventoryRequest))
                .build();
    }
}
