package com.microservice.ec.order_service.dto.response;

import com.microservice.ec.order_service.enums.PaymentMethod;
import com.microservice.ec.order_service.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PaymentResponse {

    UUID id;

    UUID userId;

    UUID orderId;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;
}
