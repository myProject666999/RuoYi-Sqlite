package com.ruoyi.ai.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ai.domain.AiChat;
import com.ruoyi.ai.mapper.AiChatMapper;
import com.ruoyi.ai.service.IAiChatService;

/**
 * AI对话记录Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class AiChatServiceImpl implements IAiChatService
{
    @Autowired
    private AiChatMapper aiChatMapper;

    @Override
    public AiChat selectAiChatById(Long chatId)
    {
        return aiChatMapper.selectAiChatById(chatId);
    }

    @Override
    public List<AiChat> selectAiChatList(AiChat aiChat)
    {
        return aiChatMapper.selectAiChatList(aiChat);
    }

    @Override
    public int insertAiChat(AiChat aiChat)
    {
        return aiChatMapper.insertAiChat(aiChat);
    }

    @Override
    public int updateAiChat(AiChat aiChat)
    {
        return aiChatMapper.updateAiChat(aiChat);
    }

    @Override
    public int deleteAiChatByIds(Long[] chatIds)
    {
        return aiChatMapper.deleteAiChatByIds(chatIds);
    }

    @Override
    public int deleteAiChatById(Long chatId)
    {
        return aiChatMapper.deleteAiChatById(chatId);
    }
}
