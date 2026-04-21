package com.ruoyi.ai.mapper;

import com.ruoyi.ai.domain.AiChatHistory;

import java.util.List;

/**
 * AI对话记录Mapper接口
 *
 * @author ruoyi
 */
public interface AiChatHistoryMapper {

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
     * 新增AI对话记录
     *
     * @param aiChatHistory AI对话记录
     * @return 结果
     */
    public int insertAiChatHistory(AiChatHistory aiChatHistory);

    /**
     * 修改AI对话记录
     *
     * @param aiChatHistory AI对话记录
     * @return 结果
     */
    public int updateAiChatHistory(AiChatHistory aiChatHistory);

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
