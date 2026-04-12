package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;

public interface IAggregatedAiService
{
    AiChatResponse chat(AiChatRequest request, Long userId);

    AiChatResponse chat(String platformCode, String prompt, Long userId);
}
