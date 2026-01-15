package com.microservice.ec.product_service.controller;

import com.microservice.ec.product_service.dto.ApiResponse;
import com.microservice.ec.product_service.dto.PageResponse;
import com.microservice.ec.product_service.dto.request.ProductRequest;
import com.microservice.ec.product_service.dto.response.ProductResponse;
import com.microservice.ec.product_service.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService productService;

    @GetMapping
    public ApiResponse<PageResponse<ProductResponse>> getAllCategories(@RequestParam(defaultValue = "1") @Min(1) int page,
                                                                       @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .message("Get all product successfully")
                .result(productService.getAllProducts(page, size))
                .build();
    }

    @GetMapping("/exists")
    public ApiResponse<Boolean> isProductExists(@RequestParam UUID id) {
        return ApiResponse.<Boolean>builder()
                .code(HttpStatus.OK.value())
                .message("Check product exists successfully")
                .result(productService.existsById(id))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<ProductResponse> createCategory(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create product successfully")
                .result(productService.createProduct(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProductResponse> updateCategory(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.ACCEPTED.value())
                .message("Update product successfully")
                .result(productService.updateProduct(request))
                .build();
    }

    @DeleteMapping("/delete/{skuCode}")
    public ApiResponse<Void> deleteProduct(@PathVariable String skuCode) {
        productService.deleteBySkuCode(skuCode);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Delete product successfully")
                .build();
    }
}

