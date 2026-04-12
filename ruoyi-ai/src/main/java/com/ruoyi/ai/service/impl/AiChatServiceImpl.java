package com.ruoyi.ai.service.impl;

import com.ruoyi.ai.domain.AiChatHistory;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.mapper.AiChatHistoryMapper;
import com.ruoyi.ai.service.IAiChatService;
import com.ruoyi.ai.service.IAiPlatformClient;
import com.ruoyi.ai.service.IAiPlatformService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AI对话Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class AiChatServiceImpl implements IAiChatService {

    private static final Logger log = LoggerFactory.getLogger(AiChatServiceImpl.class);

    @Autowired
    private AiChatHistoryMapper aiChatHistoryMapper;

    @Autowired
    private IAiPlatformService aiPlatformService;

    @Autowired
    private List<IAiPlatformClient> aiPlatformClients;

    private final Map<String, IAiPlatformClient> clientMap = new ConcurrentHashMap<>();

    @javax.annotation.PostConstruct
    public void init() {
        for (IAiPlatformClient client : aiPlatformClients) {
            clientMap.put(client.getPlatformCode(), client);
            log.info("注册AI平台客户端: {} - {}", client.getPlatformCode(), client.getPlatformName());
        }
    }

    /**
     * 发送聊天消息
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    @Override
    public AiChatResponse chat(AiChatRequest request) {
        // 获取平台配置
        AiPlatform platform = aiPlatformService.selectAiPlatformByCode(request.getPlatformCode());
        if (platform == null) {
            return AiChatResponse.error("未找到平台配置: " + request.getPlatformCode());
        }

        if ("1".equals(platform.getStatus())) {
            return AiChatResponse.error("平台已停用: " + platform.getPlatformName());
        }

        // 获取客户端
        IAiPlatformClient client = clientMap.get(request.getPlatformCode());
        if (client == null) {
            return AiChatResponse.error("未找到平台客户端: " + request.getPlatformCode());
        }

        // 生成会话ID
        if (StringUtils.isEmpty(request.getSessionId())) {
            request.setSessionId(UUID.randomUUID().toString());
        }

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 发送请求
        AiChatResponse response = client.chat(platform, request);

        // 保存对话记录
        saveChatHistory(request, response, platform, startTime);

        return response;
    }

    /**
     * 保存对话记录
     */
    private void saveChatHistory(AiChatRequest request, AiChatResponse response, AiPlatform platform, long startTime) {
        try {
            AiChatHistory history = new AiChatHistory();
            history.setPlatformId(platform.getPlatformId());
            history.setPlatformName(platform.getPlatformName());
            history.setSessionId(request.getSessionId());
            history.setUserMessage(request.getMessage());
            history.setAiResponse(response.getContent());
            history.setPromptTokens(response.getPromptTokens());
            history.setCompletionTokens(response.getCompletionTokens());
            history.setTotalTokens(response.getTotalTokens());
            history.setResponseTime(response.getResponseTime());
            history.setStatus(response.isSuccess() ? "0" : "1");
            history.setErrorMsg(response.getMessage());
            history.setCreateTime(DateUtils.getNowDate());

            aiChatHistoryMapper.insertAiChatHistory(history);
        } catch (Exception e) {
            log.error("保存对话记录失败: {}", e.getMessage());
        }
    }

    /**
     * 查询AI对话记录
     *
     * @param historyId AI对话记录ID
     * @return AI对话记录
     */
    @Override
    public AiChatHistory selectAiChatHistoryById(Long historyId) {
        return aiChatHistoryMapper.selectAiChatHistoryById(historyId);
    }

    /**
     * 查询AI对话记录列表
     *
     * @param aiChatHistory AI对话记录
     * @return AI对话记录
     */
    @Override
    public List<AiChatHistory> selectAiChatHistoryList(AiChatHistory aiChatHistory) {
        return aiChatHistoryMapper.selectAiChatHistoryList(aiChatHistory);
    }

    /**
     * 根据会话ID查询对话记录
     *
     * @param sessionId 会话ID
     * @return AI对话记录集合
     */
    @Override
    public List<AiChatHistory> selectAiChatHistoryBySessionId(String sessionId) {
        return aiChatHistoryMapper.selectAiChatHistoryBySessionId(sessionId);
    }

    /**
     * 删除AI对话记录
     *
     * @param historyId AI对话记录ID
     * @return 结果
     */
    @Override
    public int deleteAiChatHistoryById(Long historyId) {
        return aiChatHistoryMapper.deleteAiChatHistoryById(historyId);
    }

    /**
     * 批量删除AI对话记录
     *
     * @param historyIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAiChatHistoryByIds(Long[] historyIds) {
        return aiChatHistoryMapper.deleteAiChatHistoryByIds(historyIds);
    }

    /**
     * 根据会话ID删除对话记录
     *
     * @param sessionId 会话ID
     * @return 结果
     */
    @Override
    public int deleteAiChatHistoryBySessionId(String sessionId) {
        return aiChatHistoryMapper.deleteAiChatHistoryBySessionId(sessionId);
    }
}
