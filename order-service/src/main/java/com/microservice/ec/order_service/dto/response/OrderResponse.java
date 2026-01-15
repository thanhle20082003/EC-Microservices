package com.microservice.ec.order_service.dto.response;

import com.microservice.ec.order_service.entity.OrderLine;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    UUID id;

    String reference;

    private BigDecimal totalAmount;

    UUID paymentId;

    UUID customerId;

    Set<OrderLine> orderLines;
}
