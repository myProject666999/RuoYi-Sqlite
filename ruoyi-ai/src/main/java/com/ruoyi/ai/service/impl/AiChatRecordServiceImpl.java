package com.ruoyi.ai.service.impl;

import com.ruoyi.ai.domain.AiChatRecord;
import com.ruoyi.ai.mapper.AiChatRecordMapper;
import com.ruoyi.ai.service.IAiChatRecordService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiChatRecordServiceImpl implements IAiChatRecordService
{
    @Autowired
    private AiChatRecordMapper aiChatRecordMapper;

    @Override
    public AiChatRecord selectAiChatRecordByRecordId(Long recordId)
    {
        return aiChatRecordMapper.selectAiChatRecordByRecordId(recordId);
    }

    @Override
    public List<AiChatRecord> selectAiChatRecordList(AiChatRecord aiChatRecord)
    {
        return aiChatRecordMapper.selectAiChatRecordList(aiChatRecord);
    }

    @Override
    public int insertAiChatRecord(AiChatRecord aiChatRecord)
    {
        aiChatRecord.setCreateTime(DateUtils.getNowDate());
        return aiChatRecordMapper.insertAiChatRecord(aiChatRecord);
    }

    @Override
    public int deleteAiChatRecordByRecordId(Long recordId)
    {
        return aiChatRecordMapper.deleteAiChatRecordByRecordId(recordId);
    }

    @Override
    public int deleteAiChatRecordByRecordIds(Long[] recordIds)
    {
        return aiChatRecordMapper.deleteAiChatRecordByRecordIds(recordIds);
    }
}
