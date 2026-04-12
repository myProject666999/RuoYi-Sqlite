package com.ruoyi.ai.service;

import java.util.List;
import com.ruoyi.ai.domain.AiPlatform;

/**
 * AI平台配置Service接口
 * 
 * @author ruoyi
 */
public interface IAiPlatformService
{
    public AiPlatform selectAiPlatformById(Long platformId);

    public AiPlatform selectAiPlatformByCode(String platformCode);

    public List<AiPlatform> selectAiPlatformList(AiPlatform aiPlatform);

    public int insertAiPlatform(AiPlatform aiPlatform);

    public int updateAiPlatform(AiPlatform aiPlatform);

    public int deleteAiPlatformByIds(Long[] platformIds);

    public int deleteAiPlatformById(Long platformId);
}
