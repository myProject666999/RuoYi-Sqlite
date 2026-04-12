package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiPlatformConfig;

import java.util.List;

public interface IAiPlatformConfigService
{
    public AiPlatformConfig selectAiPlatformConfigByConfigId(Long configId);

    public List<AiPlatformConfig> selectAiPlatformConfigList(AiPlatformConfig aiPlatformConfig);

    public AiPlatformConfig selectAiPlatformConfigByCode(String platformCode);

    public int insertAiPlatformConfig(AiPlatformConfig aiPlatformConfig);

    public int updateAiPlatformConfig(AiPlatformConfig aiPlatformConfig);

    public int deleteAiPlatformConfigByConfigId(Long configId);

    public int deleteAiPlatformConfigByConfigIds(Long[] configIds);
}
