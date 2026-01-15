package com.microservice.ec.payment_service.dto.request;

import com.microservice.ec.payment_service.enums.PaymentMethod;
import com.microservice.ec.payment_service.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

    UUID orderId;

    UUID userId;

    PaymentMethod paymentMethod;

    BigDecimal amount;
}
