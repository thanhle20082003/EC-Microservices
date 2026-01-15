package com.microservice.ec.order_service.dto.response;

import com.microservice.ec.order_service.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderLineResponse {

    UUID id;

    Order order;

    UUID productId;

    Long quantity;
}
