package com.david.aiquery.controller;

import com.david.aiquery.ai.dto.AIQueryRequestDto;
import com.david.aiquery.ai.dto.AIQueryResponseDto;
import com.david.aiquery.ai.service.AIQueryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIQueryController {
    private final AIQueryService aiQueryService;

    public AIQueryController(AIQueryService aiQueryService) {
        this.aiQueryService = aiQueryService;
    }

    @PostMapping("/query")
    public AIQueryResponseDto generateQueryResponse(@Valid @RequestBody AIQueryRequestDto requestDto){
        return aiQueryService.generateQueryResponse(requestDto.query());
    }

    //Comprobar que el backend está funcionando
    @GetMapping("/health")
    public String health(){
        return "AIQuery API running";
    }
}
