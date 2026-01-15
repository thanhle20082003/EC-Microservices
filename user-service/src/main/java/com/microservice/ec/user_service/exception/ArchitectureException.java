package com.microservice.ec.user_service.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArchitectureException extends RuntimeException {

    ErrorCode errorCode;
    String message;

    public ArchitectureException(ErrorCode errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = errorCode.format(message);
    }
}
