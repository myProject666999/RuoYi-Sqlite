package com.ruoyi.ai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatformConfig;
import com.ruoyi.ai.service.IAiService;
import com.ruoyi.ai.utils.OkHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("qwenAiService")
public class QwenAiService implements IAiService
{
    private static final Logger log = LoggerFactory.getLogger(QwenAiService.class);

    private static final String PLATFORM_CODE = "qwen";

    @Override
    public String getPlatformCode()
    {
        return PLATFORM_CODE;
    }

    @Override
    public AiChatResponse chat(AiChatRequest request, AiPlatformConfig config)
    {
        AiChatResponse response = new AiChatResponse();

        try
        {
            String apiUrl = config.getApiUrl();
            String apiKey = config.getApiKey();
            String model = request.getModel() != null ? request.getModel() : config.getModel();

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", model);

            JSONObject input = new JSONObject();
            JSONArray messages = new JSONArray();
            for (AiChatRequest.Message msg : request.getMessages())
            {
                JSONObject message = new JSONObject();
                message.put("role", msg.getRole());
                message.put("content", msg.getContent());
                messages.add(message);
            }
            input.put("messages", messages);
            requestBody.put("input", input);

            JSONObject parameters = new JSONObject();
            if (request.getTemperature() != null)
            {
                parameters.put("temperature", request.getTemperature());
            }
            else if (config.getTemperature() != null)
            {
                parameters.put("temperature", config.getTemperature());
            }
            if (request.getMaxTokens() != null)
            {
                parameters.put("max_tokens", request.getMaxTokens());
            }
            else if (config.getMaxTokens() != null)
            {
                parameters.put("max_tokens", config.getMaxTokens());
            }
            parameters.put("result_format", "message");
            requestBody.put("parameters", parameters);

            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + apiKey);
            headers.put("Content-Type", "application/json");

            String jsonResponse = OkHttpUtils.post(apiUrl, requestBody.toJSONString(), headers);
            JSONObject result = JSON.parseObject(jsonResponse);

            JSONObject output = result.getJSONObject("output");
            JSONArray choices = output.getJSONArray("choices");
            if (choices != null && !choices.isEmpty())
            {
                JSONObject choice = choices.getJSONObject(0);
                JSONObject message = choice.getJSONObject("message");
                response.setContent(message.getString("content"));
            }

            JSONObject usage = result.getJSONObject("usage");
            if (usage != null)
            {
                response.setPromptTokens(usage.getInteger("input_tokens"));
                response.setCompletionTokens(usage.getInteger("output_tokens"));
                response.setTotalTokens(usage.getInteger("total_tokens"));
            }

            response.setModel(model);
        }
        catch (Exception e)
        {
            log.error("千问API调用失败", e);
            response.setSuccess(false);
            response.setErrorMsg(e.getMessage());
        }

        return response;
    }
}
