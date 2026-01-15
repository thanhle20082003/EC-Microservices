package com.microservice.ec.product_service.service;

import com.microservice.ec.product_service.dto.PageResponse;
import com.microservice.ec.product_service.dto.request.ProductRequest;
import com.microservice.ec.product_service.dto.response.ProductResponse;
import com.microservice.ec.product_service.entity.Category;
import com.microservice.ec.product_service.entity.Product;
import com.microservice.ec.product_service.exception.ArchitectureException;
import com.microservice.ec.product_service.exception.ErrorCode;
import com.microservice.ec.product_service.mapper.ProductMapper;
import com.microservice.ec.product_service.repository.CategoryRepository;
import com.microservice.ec.product_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    public PageResponse<ProductResponse> getAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Product> products = productRepository.findAll(pageable);

        var data = products.getContent()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();

        return PageResponse.<ProductResponse>builder()
                .currentPages(page)
                .pageSizes(size)
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .data(data)
                .build();
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Category"));
        Product product = productMapper.toProduct(productRequest);
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(ProductRequest productRequest) {
        String skuCode = productRequest.getSkuCode();
        var product = productRepository.findBySkuCode(skuCode).orElseThrow(() -> new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Product"));
        productMapper.updateProduct(product, productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

    @Transactional
    public void deleteBySkuCode(String skuCode) {
        if (!productRepository.existsBySkuCode(skuCode))
            throw new ArchitectureException(ErrorCode.ENTITY_NOT_FOUND, "Product");
        productRepository.deleteBySkuCode(skuCode);
    }
}
