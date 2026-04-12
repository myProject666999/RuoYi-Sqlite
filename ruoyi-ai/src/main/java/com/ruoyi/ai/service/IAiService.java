package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatformConfig;

public interface IAiService
{
    AiChatResponse chat(AiChatRequest request, AiPlatformConfig config);

    String getPlatformCode();
}
