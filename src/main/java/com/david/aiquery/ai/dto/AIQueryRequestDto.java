package com.david.aiquery.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AIQueryRequestDto(
        @NotBlank(message = "Query cannot be empty")
        @Size(max = 1000, message = "Query too long")
        String query,
        @NotBlank
        String conversationId
) {
}
