package com.microservice.ec.order_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderRequest {

    String reference;
    @Positive(message = "Order amount should be positive")
    BigDecimal amount;
    @NotNull(message = "Payment method should be precised")
    UUID paymentId;
    @NotNull(message = "Customer should be present")
    @NotEmpty(message = "Customer should be present")
    @NotBlank(message = "Customer should be present")
    String customerId;
//    @NotEmpty(message = "You should at least purchase one product")
//    List<PurchaseRequest> products
}
