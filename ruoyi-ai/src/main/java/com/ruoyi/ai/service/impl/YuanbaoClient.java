package com.ruoyi.ai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatform;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 腾讯元宝客户端
 *
 * @author ruoyi
 */
@Component
public class YuanbaoClient extends AbstractAiPlatformClient {

    private static final String PLATFORM_CODE = "yuanbao";
    private static final String PLATFORM_NAME = "腾讯元宝";

    @Override
    public String getPlatformCode() {
        return PLATFORM_CODE;
    }

    @Override
    public String getPlatformName() {
        return PLATFORM_NAME;
    }

    @Override
    protected Map<String, String> buildHeaders(String apiKey) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + apiKey);
        return headers;
    }

    @Override
    protected String buildRequestBody(AiPlatform platform, AiChatRequest request) {
        JSONObject body = new JSONObject();
        body.put("model", platform.getModelName());

        // 构建消息数组
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", request.getMessage());
        messages.add(message);
        body.put("messages", messages);

        // 设置参数
        if (request.getTemperature() != null) {
            body.put("temperature", request.getTemperature());
        }
        if (request.getMaxTokens() != null) {
            body.put("max_tokens", request.getMaxTokens());
        }

        // 生成会话ID
        if (request.getSessionId() == null) {
            request.setSessionId(UUID.randomUUID().toString());
        }

        return body.toJSONString();
    }

    @Override
    protected AiChatResponse parseResponse(String responseBody) {
        JSONObject json = JSON.parseObject(responseBody);
        AiChatResponse response = new AiChatResponse();

        // 腾讯元宝API响应格式与OpenAI兼容
        if (json.containsKey("choices")) {
            JSONArray choices = json.getJSONArray("choices");
            if (!choices.isEmpty()) {
                JSONObject choice = choices.getJSONObject(0);
                JSONObject message = choice.getJSONObject("message");
                response.setContent(message.getString("content"));
            }
        }

        // 解析token使用情况
        if (json.containsKey("usage")) {
            JSONObject usage = json.getJSONObject("usage");
            response.setPromptTokens(usage.getInteger("prompt_tokens"));
            response.setCompletionTokens(usage.getInteger("completion_tokens"));
            response.setTotalTokens(usage.getInteger("total_tokens"));
        }

        return response;
    }
}
