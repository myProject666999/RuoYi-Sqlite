package com.ruoyi.ai.service.impl;

import com.ruoyi.ai.domain.AiPlatformConfig;
import com.ruoyi.ai.mapper.AiPlatformConfigMapper;
import com.ruoyi.ai.service.IAiPlatformConfigService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiPlatformConfigServiceImpl implements IAiPlatformConfigService
{
    @Autowired
    private AiPlatformConfigMapper aiPlatformConfigMapper;

    @Override
    public AiPlatformConfig selectAiPlatformConfigByConfigId(Long configId)
    {
        return aiPlatformConfigMapper.selectAiPlatformConfigByConfigId(configId);
    }

    @Override
    public List<AiPlatformConfig> selectAiPlatformConfigList(AiPlatformConfig aiPlatformConfig)
    {
        return aiPlatformConfigMapper.selectAiPlatformConfigList(aiPlatformConfig);
    }

    @Override
    public AiPlatformConfig selectAiPlatformConfigByCode(String platformCode)
    {
        return aiPlatformConfigMapper.selectAiPlatformConfigByCode(platformCode);
    }

    @Override
    public int insertAiPlatformConfig(AiPlatformConfig aiPlatformConfig)
    {
        aiPlatformConfig.setCreateTime(DateUtils.getNowDate());
        return aiPlatformConfigMapper.insertAiPlatformConfig(aiPlatformConfig);
    }

    @Override
    public int updateAiPlatformConfig(AiPlatformConfig aiPlatformConfig)
    {
        aiPlatformConfig.setUpdateTime(DateUtils.getNowDate());
        return aiPlatformConfigMapper.updateAiPlatformConfig(aiPlatformConfig);
    }

    @Override
    public int deleteAiPlatformConfigByConfigId(Long configId)
    {
        return aiPlatformConfigMapper.deleteAiPlatformConfigByConfigId(configId);
    }

    @Override
    public int deleteAiPlatformConfigByConfigIds(Long[] configIds)
    {
        return aiPlatformConfigMapper.deleteAiPlatformConfigByConfigIds(configIds);
    }
}
