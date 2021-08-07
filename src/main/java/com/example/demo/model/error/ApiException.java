package com.example.demo.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException{

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public ApiException(ErrorCode errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getDescription();
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
