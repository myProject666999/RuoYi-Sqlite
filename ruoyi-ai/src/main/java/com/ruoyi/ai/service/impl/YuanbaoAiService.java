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

@Service("yuanbaoAiService")
public class YuanbaoAiService implements IAiService
{
    private static final Logger log = LoggerFactory.getLogger(YuanbaoAiService.class);

    private static final String PLATFORM_CODE = "yuanbao";

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

            JSONArray messages = new JSONArray();
            for (AiChatRequest.Message msg : request.getMessages())
            {
                JSONObject message = new JSONObject();
                message.put("role", msg.getRole());
                message.put("content", msg.getContent());
                messages.add(message);
            }
            requestBody.put("messages", messages);

            if (request.getTemperature() != null)
            {
                requestBody.put("temperature", request.getTemperature());
            }
            else if (config.getTemperature() != null)
            {
                requestBody.put("temperature", config.getTemperature());
            }

            if (request.getMaxTokens() != null)
            {
                requestBody.put("max_output_tokens", request.getMaxTokens());
            }
            else if (config.getMaxTokens() != null)
            {
                requestBody.put("max_output_tokens", config.getMaxTokens());
            }

            requestBody.put("stream", false);

            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + apiKey);
            headers.put("Content-Type", "application/json");

            String jsonResponse = OkHttpUtils.post(apiUrl, requestBody.toJSONString(), headers);
            JSONObject result = JSON.parseObject(jsonResponse);

            if (result.containsKey("error_code"))
            {
                response.setSuccess(false);
                response.setErrorMsg(result.getString("error_msg"));
                return response;
            }

            response.setContent(result.getString("result"));

            JSONObject usage = result.getJSONObject("usage");
            if (usage != null)
            {
                response.setPromptTokens(usage.getInteger("prompt_tokens"));
                response.setCompletionTokens(usage.getInteger("completion_tokens"));
                response.setTotalTokens(usage.getInteger("total_tokens"));
            }

            response.setModel(model);
        }
        catch (Exception e)
        {
            log.error("元宝API调用失败", e);
            response.setSuccess(false);
            response.setErrorMsg(e.getMessage());
        }

        return response;
    }
}
