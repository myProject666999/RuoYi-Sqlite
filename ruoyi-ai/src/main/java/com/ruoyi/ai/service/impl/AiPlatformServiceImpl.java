package com.ruoyi.ai.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.mapper.AiPlatformMapper;
import com.ruoyi.ai.service.IAiPlatformService;

/**
 * AI平台配置Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class AiPlatformServiceImpl implements IAiPlatformService
{
    @Autowired
    private AiPlatformMapper aiPlatformMapper;

    @Override
    public AiPlatform selectAiPlatformById(Long platformId)
    {
        return aiPlatformMapper.selectAiPlatformById(platformId);
    }

    @Override
    public AiPlatform selectAiPlatformByCode(String platformCode)
    {
        return aiPlatformMapper.selectAiPlatformByCode(platformCode);
    }

    @Override
    public List<AiPlatform> selectAiPlatformList(AiPlatform aiPlatform)
    {
        return aiPlatformMapper.selectAiPlatformList(aiPlatform);
    }

    @Override
    public int insertAiPlatform(AiPlatform aiPlatform)
    {
        return aiPlatformMapper.insertAiPlatform(aiPlatform);
    }

    @Override
    public int updateAiPlatform(AiPlatform aiPlatform)
    {
        return aiPlatformMapper.updateAiPlatform(aiPlatform);
    }

    @Override
    public int deleteAiPlatformByIds(Long[] platformIds)
    {
        return aiPlatformMapper.deleteAiPlatformByIds(platformIds);
    }

    @Override
    public int deleteAiPlatformById(Long platformId)
    {
        return aiPlatformMapper.deleteAiPlatformById(platformId);
    }
}
