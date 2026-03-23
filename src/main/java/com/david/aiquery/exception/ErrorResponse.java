package com.david.aiquery.exception;

public record ErrorResponse(
        String message,
        int status
) {
}
