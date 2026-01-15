package com.microservice.ec.inventory_service.client;

import com.microservice.ec.inventory_service.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/exists")
    ApiResponse<Boolean> isProductExists(@RequestParam UUID id);
}
