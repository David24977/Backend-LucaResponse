package com.david.aiquery.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AIQueryRequestDto(
        @NotBlank(message = "Query cannot be empty")
        @Size(max = 4000, message = "Query too long(pregunta demasiado larga)")
        String query,
        @NotBlank
        String conversationId
) {
}
