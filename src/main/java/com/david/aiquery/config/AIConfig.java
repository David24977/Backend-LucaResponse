package com.david.aiquery.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(12)
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        // Creamos las opciones manualmente
        // IMPORTANTE: Si .googleSearch() te da error, usa .setGoogleSearch() después del build o busca el nombre exacto en tu IDE
        var options = GoogleGenAiChatOptions.builder()
                .model("gemini-3.1-flash-lite-preview")
                .temperature(0.2)
                .build();

        // Este es el truco para activar el Grounding en la versión 1.1.2
        options.setGoogleSearchRetrieval(true);

        return ChatClient.builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultOptions(options)
                .build();
    }
}