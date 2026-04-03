package com.david.aiquery.ai.service;

import com.david.aiquery.ai.dto.AIQueryResponseDto;

public interface AIQueryService {

    AIQueryResponseDto generateQueryResponse(String query, String conversationId);
}
