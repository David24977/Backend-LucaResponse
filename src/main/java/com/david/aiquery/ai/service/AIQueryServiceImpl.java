package com.david.aiquery.ai.service;

import com.david.aiquery.ai.dto.AIQueryResponseDto;
import com.david.aiquery.ai.prompt.AIPrompts;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AIQueryServiceImpl implements AIQueryService {

    private final ChatClient chatClient;

    public AIQueryServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public AIQueryResponseDto generateQueryResponse(String query, String conversationId) {

        String response = chatClient
                    .prompt()
                    .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                    .system(AIPrompts.SYSTEM_PROMPT)
                    .user(query)
                    .call()
                    .content();

            if (response == null || response.isBlank()) {
                throw new IllegalStateException("AI returned empty response");
            }

            return new AIQueryResponseDto(response.trim());

    }
}