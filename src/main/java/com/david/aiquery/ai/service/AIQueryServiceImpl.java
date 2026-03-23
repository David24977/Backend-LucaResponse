package com.david.aiquery.ai.service;

import com.david.aiquery.ai.dto.AIQueryResponseDto;
import com.david.aiquery.ai.prompt.AIPrompts;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIQueryServiceImpl implements AIQueryService {
    private final ChatClient chatClient;


    public AIQueryServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @Override
    public AIQueryResponseDto generateQueryResponse(String query) {
        String userPrompt = AIPrompts.USER_PROMPT.formatted(query);//Utilizo la clase AIPrompts
        String response = chatClient
                .prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user(userPrompt)
                .call()
                .content();
        if (response == null) {
            throw new RuntimeException("AI returned empty response");
        }
        response = response
                .replaceAll("\\s+", " ")
                .trim();

        return  new AIQueryResponseDto(response);


    }
}

