package com.ruoyi.ai.mapper;

import com.ruoyi.ai.domain.AiChatRecord;

import java.util.List;

public interface AiChatRecordMapper
{
    public AiChatRecord selectAiChatRecordByRecordId(Long recordId);

    public List<AiChatRecord> selectAiChatRecordList(AiChatRecord aiChatRecord);

    public int insertAiChatRecord(AiChatRecord aiChatRecord);

    public int deleteAiChatRecordByRecordId(Long recordId);

    public int deleteAiChatRecordByRecordIds(Long[] recordIds);
}
