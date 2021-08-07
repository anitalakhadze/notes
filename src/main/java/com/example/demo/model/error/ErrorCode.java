package com.example.demo.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND("not.found", "Entity not found"),
    FORBIDDEN("forbidden", "Non sufficient privileges"),
    UNAUTHORIZED("unauthorized", "Authentication is required"),
    INVALID_REQUEST("invalid.request", "Invalid request");

    private final String code;
    private final String description;
}
