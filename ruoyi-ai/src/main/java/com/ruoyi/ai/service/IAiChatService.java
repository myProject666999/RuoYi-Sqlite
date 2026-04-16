package com.ruoyi.ai.service;

import java.util.List;
import com.ruoyi.ai.domain.AiChat;

/**
 * AI对话记录Service接口
 * 
 * @author ruoyi
 */
public interface IAiChatService
{
    public AiChat selectAiChatById(Long chatId);

    public List<AiChat> selectAiChatList(AiChat aiChat);

    public int insertAiChat(AiChat aiChat);

    public int updateAiChat(AiChat aiChat);

    public int deleteAiChatByIds(Long[] chatIds);

    public int deleteAiChatById(Long chatId);
}
