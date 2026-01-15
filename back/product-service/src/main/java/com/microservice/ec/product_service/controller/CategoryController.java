package com.microservice.ec.product_service.controller;

import com.microservice.ec.product_service.dto.ApiResponse;
import com.microservice.ec.product_service.dto.PageResponse;
import com.microservice.ec.product_service.dto.request.CategoryRequest;
import com.microservice.ec.product_service.dto.response.CategoryResponse;
import com.microservice.ec.product_service.service.CategoryService;
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
@RequestMapping("/api/categories")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public ApiResponse<PageResponse<CategoryResponse>> getAllCategories(@RequestParam(defaultValue = "1") @Min(1) int page,
                                                                        @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        return ApiResponse.<PageResponse<CategoryResponse>>builder()
                .message("Get all category successfully")
                .result(categoryService.getAllCategories(page, size))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create category successfully")
                .result(categoryService.createCategory(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.ACCEPTED.value())
                .message("Update category successfully")
                .result(categoryService.updateCategory(request))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Delete category successfully")
                .build();
    }
}
