package com.ruoyi.ai.mapper;

import java.util.List;
import com.ruoyi.ai.domain.AiPlatform;

/**
 * AI平台配置Mapper接口
 * 
 * @author ruoyi
 */
public interface AiPlatformMapper
{
    public AiPlatform selectAiPlatformById(Long platformId);

    public AiPlatform selectAiPlatformByCode(String platformCode);

    public List<AiPlatform> selectAiPlatformList(AiPlatform aiPlatform);

    public int insertAiPlatform(AiPlatform aiPlatform);

    public int updateAiPlatform(AiPlatform aiPlatform);

    public int deleteAiPlatformById(Long platformId);

    public int deleteAiPlatformByIds(Long[] platformIds);
}
