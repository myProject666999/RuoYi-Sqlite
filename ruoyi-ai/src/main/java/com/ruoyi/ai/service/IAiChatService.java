package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiChatHistory;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;

import java.util.List;

/**
 * AI对话Service接口
 *
 * @author ruoyi
 */
public interface IAiChatService {

    /**
     * 发送聊天消息
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    public AiChatResponse chat(AiChatRequest request);

    /**
     * 查询AI对话记录
     *
     * @param historyId AI对话记录ID
     * @return AI对话记录
     */
    public AiChatHistory selectAiChatHistoryById(Long historyId);

    /**
     * 查询AI对话记录列表
     *
     * @param aiChatHistory AI对话记录
     * @return AI对话记录集合
     */
    public List<AiChatHistory> selectAiChatHistoryList(AiChatHistory aiChatHistory);

    /**
     * 根据会话ID查询对话记录
     *
     * @param sessionId 会话ID
     * @return AI对话记录集合
     */
    public List<AiChatHistory> selectAiChatHistoryBySessionId(String sessionId);

    /**
     * 删除AI对话记录
     *
     * @param historyId AI对话记录ID
     * @return 结果
     */
    public int deleteAiChatHistoryById(Long historyId);

    /**
     * 批量删除AI对话记录
     *
     * @param historyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAiChatHistoryByIds(Long[] historyIds);

    /**
     * 根据会话ID删除对话记录
     *
     * @param sessionId 会话ID
     * @return 结果
     */
    public int deleteAiChatHistoryBySessionId(String sessionId);
}
