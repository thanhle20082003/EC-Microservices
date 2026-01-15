package com.microservice.ec.product_service.service;

import com.microservice.ec.product_service.dto.PageResponse;
import com.microservice.ec.product_service.exception.ArchitectureException;
import com.microservice.ec.product_service.exception.ErrorCode;
import com.microservice.ec.product_service.mapper.CategoryMapper;
import com.microservice.ec.product_service.dto.request.CategoryRequest;
import com.microservice.ec.product_service.dto.response.CategoryResponse;
import com.microservice.ec.product_service.entity.Category;
import com.microservice.ec.product_service.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public PageResponse<CategoryResponse> getAllCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Category> categories = categoryRepository.findAll(pageable);

        var data = categories.getContent()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

        return PageResponse.<CategoryResponse>builder()
                .currentPages(page)
                .pageSizes(size)
                .totalPages(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .data(data)
                .build();
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        String name = categoryRequest.getName();
        var category = categoryRepository.findByName(name).orElseThrow();
        categoryMapper.updateCategory(category, categoryRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public void deleteById(UUID id) {
        if (!categoryRepository.existsById(id))
            throw new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Category");
        categoryRepository.deleteById(id);
    }
}
