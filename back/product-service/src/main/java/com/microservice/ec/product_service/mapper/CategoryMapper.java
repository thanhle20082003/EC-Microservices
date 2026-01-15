package com.microservice.ec.product_service.mapper;

import com.microservice.ec.product_service.dto.request.CategoryRequest;
import com.microservice.ec.product_service.dto.response.CategoryResponse;
import com.microservice.ec.product_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
