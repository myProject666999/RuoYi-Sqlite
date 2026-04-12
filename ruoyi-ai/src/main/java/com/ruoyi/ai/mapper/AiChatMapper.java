package com.ruoyi.ai.mapper;

import java.util.List;
import com.ruoyi.ai.domain.AiChat;

/**
 * AI对话记录Mapper接口
 * 
 * @author ruoyi
 */
public interface AiChatMapper
{
    public AiChat selectAiChatById(Long chatId);

    public List<AiChat> selectAiChatList(AiChat aiChat);

    public int insertAiChat(AiChat aiChat);

    public int updateAiChat(AiChat aiChat);

    public int deleteAiChatById(Long chatId);

    public int deleteAiChatByIds(Long[] chatIds);
}
