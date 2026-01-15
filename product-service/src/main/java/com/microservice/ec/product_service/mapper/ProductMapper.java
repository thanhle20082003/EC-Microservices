package com.microservice.ec.product_service.mapper;

import com.microservice.ec.product_service.dto.request.ProductRequest;
import com.microservice.ec.product_service.dto.response.ProductResponse;
import com.microservice.ec.product_service.entity.Category;
import com.microservice.ec.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Product toProduct(ProductRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateProduct(@MappingTarget Product product, ProductRequest request);
}
