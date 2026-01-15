package com.microservice.ec.product_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ENTITY_NOT_FOUND(404, "%s not found"),
    ENTITY_ALREADY_EXISTS(400, "%s already exists"),
    INVALID_REQUEST(400, "Invalid request data"),
    UNAUTHORIZED(401, "Unauthorized access"),
    FORBIDDEN(403, "Forbidden access"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int code;
    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
