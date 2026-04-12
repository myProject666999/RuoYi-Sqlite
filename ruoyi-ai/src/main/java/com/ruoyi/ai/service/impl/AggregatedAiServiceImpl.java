package com.ruoyi.ai.service.impl;

import com.ruoyi.ai.domain.AiChatRecord;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatformConfig;
import com.ruoyi.ai.factory.AiServiceFactory;
import com.ruoyi.ai.service.IAggregatedAiService;
import com.ruoyi.ai.service.IAiChatRecordService;
import com.ruoyi.ai.service.IAiPlatformConfigService;
import com.ruoyi.ai.service.IAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AggregatedAiServiceImpl implements IAggregatedAiService
{
    @Autowired
    private AiServiceFactory aiServiceFactory;

    @Autowired
    private IAiPlatformConfigService aiPlatformConfigService;

    @Autowired
    private IAiChatRecordService aiChatRecordService;

    @Override
    public AiChatResponse chat(AiChatRequest request, Long userId)
    {
        long startTime = System.currentTimeMillis();
        String platformCode = request.getPlatformCode();

        AiPlatformConfig config = aiPlatformConfigService.selectAiPlatformConfigByCode(platformCode);
        if (config == null)
        {
            return new AiChatResponse("未找到平台配置: " + platformCode);
        }

        IAiService aiService = aiServiceFactory.getService(platformCode);
        if (aiService == null)
        {
            return new AiChatResponse("不支持的AI平台: " + platformCode);
        }

        AiChatResponse response = aiService.chat(request, config);

        AiChatRecord record = new AiChatRecord();
        record.setPlatformCode(platformCode);
        record.setModel(response.getModel());
        record.setUserId(userId);
        record.setPrompt(getLastUserMessage(request.getMessages()));
        record.setResponse(response.getContent());
        record.setPromptTokens(response.getPromptTokens());
        record.setResponseTokens(response.getCompletionTokens());
        record.setTotalTokens(response.getTotalTokens());
        record.setExecTime((int) (System.currentTimeMillis() - startTime));
        record.setErrorMsg(response.isSuccess() ? null : response.getErrorMsg());
        record.setCreateTime(new Date());
        aiChatRecordService.insertAiChatRecord(record);

        return response;
    }

    @Override
    public AiChatResponse chat(String platformCode, String prompt, Long userId)
    {
        AiChatRequest request = new AiChatRequest();
        request.setPlatformCode(platformCode);
        List<AiChatRequest.Message> messages = new ArrayList<>();
        messages.add(new AiChatRequest.Message("user", prompt));
        request.setMessages(messages);
        return chat(request, userId);
    }

    private String getLastUserMessage(List<AiChatRequest.Message> messages)
    {
        for (int i = messages.size() - 1; i >= 0; i--)
        {
            if ("user".equals(messages.get(i).getRole()))
            {
                return messages.get(i).getContent();
            }
        }
        return messages.isEmpty() ? "" : messages.get(messages.size() - 1).getContent();
    }
}
