package com.microservice.ec.payment_service.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    int code;
    String error;
    String message;
    String path;

}
