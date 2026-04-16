package com.ruoyi.ai.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ai.client.AiClient;
import com.ruoyi.ai.domain.AiChat;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.mapper.AiPlatformMapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * AI服务统一调用Service
 * 
 * @author ruoyi
 */
@Service
public class AiService
{
    private static final Logger log = LoggerFactory.getLogger(AiService.class);

    @Autowired
    private AiPlatformMapper aiPlatformMapper;

    @Autowired
    private IAiChatService aiChatService;

    @Autowired
    private Map<String, AiClient> aiClientMap = new HashMap<>();

    public AiChat chat(String platformCode, String model, String message)
    {
        AiPlatform platform = aiPlatformMapper.selectAiPlatformByCode(platformCode);
        if (platform == null)
        {
            throw new ServiceException("未找到AI平台配置: " + platformCode);
        }
        
        if (!"0".equals(platform.getStatus()))
        {
            throw new ServiceException("AI平台已停用: " + platform.getPlatformName());
        }

        AiClient client = aiClientMap.get(platformCode + "Client");
        if (client == null)
        {
            throw new ServiceException("未找到AI客户端实现: " + platformCode);
        }

        if (model == null || model.isEmpty())
        {
            model = platform.getDefaultModel();
        }

        AiChat chat = client.chat(platform.getApiKey(), platform.getApiUrl(), model, message);
        
        chat.setPlatformId(platform.getPlatformId());
        chat.setPlatformName(platform.getPlatformName());
        chat.setCreateTime(new Date());
        
        try
        {
            chat.setUserId(ShiroUtils.getUserId());
            chat.setUserName(ShiroUtils.getLoginName());
        }
        catch (Exception e)
        {
            log.warn("获取用户信息失败: {}", e.getMessage());
        }
        
        aiChatService.insertAiChat(chat);
        
        return chat;
    }

    public AiChat chatByPlatformId(Long platformId, String model, String message)
    {
        AiPlatform platform = aiPlatformMapper.selectAiPlatformById(platformId);
        if (platform == null)
        {
            throw new ServiceException("未找到AI平台配置: " + platformId);
        }
        
        return chat(platform.getPlatformCode(), model, message);
    }

    public List<AiPlatform> getAvailablePlatforms()
    {
        AiPlatform query = new AiPlatform();
        query.setStatus("0");
        return aiPlatformMapper.selectAiPlatformList(query);
    }
}
