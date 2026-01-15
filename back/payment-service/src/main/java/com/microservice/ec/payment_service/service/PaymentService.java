package com.microservice.ec.payment_service.service;

import com.microservice.ec.payment_service.dto.request.PaymentRequest;
import com.microservice.ec.payment_service.dto.response.PaymentResponse;
import com.microservice.ec.payment_service.enums.PaymentStatus;
import com.microservice.ec.payment_service.mapper.PaymentMapper;
import com.microservice.ec.payment_service.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {

    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest request) {
        var payment = paymentMapper.toPayment(request);
        payment.setStatus( Math.random() > 0.2 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED );
        return paymentMapper.toPaymentResponse(payment);
    }
}
