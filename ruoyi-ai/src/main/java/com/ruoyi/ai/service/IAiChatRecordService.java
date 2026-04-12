package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiChatRecord;

import java.util.List;

public interface IAiChatRecordService
{
    public AiChatRecord selectAiChatRecordByRecordId(Long recordId);

    public List<AiChatRecord> selectAiChatRecordList(AiChatRecord aiChatRecord);

    public int insertAiChatRecord(AiChatRecord aiChatRecord);

    public int deleteAiChatRecordByRecordId(Long recordId);

    public int deleteAiChatRecordByRecordIds(Long[] recordIds);
}
