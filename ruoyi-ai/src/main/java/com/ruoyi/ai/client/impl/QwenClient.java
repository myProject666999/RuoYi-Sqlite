package com.ruoyi.ai.client.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ai.client.AiClient;
import com.ruoyi.ai.client.HttpUtil;
import com.ruoyi.ai.domain.AiChat;

/**
 * 千问(Qwen)客户端实现
 * 阿里云通义千问API
 * 
 * @author ruoyi
 */
@Component
public class QwenClient implements AiClient
{
    private static final Logger log = LoggerFactory.getLogger(QwenClient.class);

    private static final String PLATFORM_CODE = "qwen";
    private static final String DEFAULT_API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
    private static final String DEFAULT_MODEL = "qwen-turbo";

    @Override
    public String getPlatformCode()
    {
        return PLATFORM_CODE;
    }

    @Override
    public AiChat chat(String apiKey, String apiUrl, String model, String message)
    {
        if (apiUrl == null || apiUrl.isEmpty())
        {
            apiUrl = DEFAULT_API_URL;
        }
        if (model == null || model.isEmpty())
        {
            model = DEFAULT_MODEL;
        }

        AiChat chat = new AiChat();
        chat.setModelName(model);
        chat.setUserMessage(message);

        try
        {
            JSONObject requestBody = buildRequestBody(model, message);
            Map<String, String> headers = buildHeaders(apiKey);

            JSONObject response = HttpUtil.doPostJson(apiUrl, requestBody, headers);
            
            if (response != null)
            {
                return parseResponse(chat, response);
            }
        }
        catch (Exception e)
        {
            log.error("千问API调用失败: {}", e.getMessage(), e);
            chat.setAiResponse("调用千问API失败: " + e.getMessage());
        }

        return chat;
    }

    @Override
    public String chatStream(String apiKey, String apiUrl, String model, String message)
    {
        return null;
    }

    private JSONObject buildRequestBody(String model, String message)
    {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        
        JSONObject input = new JSONObject();
        JSONArray messages = new JSONArray();
        
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", message);
        messages.add(userMessage);
        
        input.put("messages", messages);
        requestBody.put("input", input);
        
        JSONObject parameters = new JSONObject();
        parameters.put("result_format", "message");
        requestBody.put("parameters", parameters);
        
        return requestBody;
    }

    private Map<String, String> buildHeaders(String apiKey)
    {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private AiChat parseResponse(AiChat chat, JSONObject response)
    {
        try
        {
            if (response.containsKey("output"))
            {
                JSONObject output = response.getJSONObject("output");
                if (output.containsKey("choices"))
                {
                    JSONArray choices = output.getJSONArray("choices");
                    if (choices != null && !choices.isEmpty())
                    {
                        JSONObject choice = choices.getJSONObject(0);
                        JSONObject message = choice.getJSONObject("message");
                        if (message != null)
                        {
                            chat.setAiResponse(message.getString("content"));
                        }
                    }
                }
                else if (output.containsKey("text"))
                {
                    chat.setAiResponse(output.getString("text"));
                }
            }
            
            if (response.containsKey("usage"))
            {
                JSONObject usage = response.getJSONObject("usage");
                if (usage.containsKey("total_tokens"))
                {
                    chat.setTotalTokens(usage.getLong("total_tokens"));
                }
            }
        }
        catch (Exception e)
        {
            log.error("解析千问响应失败: {}", e.getMessage(), e);
            chat.setAiResponse("解析响应失败: " + e.getMessage());
        }
        
        return chat;
    }
}
