package com.ruoyi.ai.client;

import com.ruoyi.ai.domain.AiChat;

/**
 * AI客户端接口
 * 
 * @author ruoyi
 */
public interface AiClient
{
    String getPlatformCode();

    AiChat chat(String apiKey, String apiUrl, String model, String message);

    String chatStream(String apiKey, String apiUrl, String model, String message);
}
