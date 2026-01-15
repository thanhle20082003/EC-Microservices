package com.microservice.ec.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private int currentPages;
    private int pageSizes;
    private int totalPages;
    private long totalElements;
    @Builder.Default
    private List<T> data = Collections.emptyList();
}

