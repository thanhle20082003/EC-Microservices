package com.microservice.ec.payment_service.controller;

import com.microservice.ec.payment_service.dto.ApiResponse;
import com.microservice.ec.payment_service.dto.request.PaymentRequest;
import com.microservice.ec.payment_service.dto.response.PaymentResponse;
import com.microservice.ec.payment_service.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/payments")
public class PaymentController {

    PaymentService paymentService;

    @PostMapping("/create")
    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Payment created successfully")
                .result(paymentService.createPayment(request))
                .build();
    }
}
