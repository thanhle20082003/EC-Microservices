package com.microservice.ec.payment_service.entity;

import com.microservice.ec.payment_service.enums.PaymentMethod;
import com.microservice.ec.payment_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    UUID userId;

    UUID orderId;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
