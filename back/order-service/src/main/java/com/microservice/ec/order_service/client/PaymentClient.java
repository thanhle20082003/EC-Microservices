package com.microservice.ec.order_service.client;

import com.microservice.ec.order_service.dto.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("api/payments/create")
    PaymentResponse createPayment(@RequestBody PaymentRequest request);
}
