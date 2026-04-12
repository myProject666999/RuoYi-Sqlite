package com.ruoyi.ai.service.impl;

import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.mapper.AiPlatformMapper;
import com.ruoyi.ai.service.IAiPlatformService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AI平台配置Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class AiPlatformServiceImpl implements IAiPlatformService {

    @Autowired
    private AiPlatformMapper aiPlatformMapper;

    /**
     * 查询AI平台配置
     *
     * @param platformId AI平台配置ID
     * @return AI平台配置
     */
    @Override
    public AiPlatform selectAiPlatformById(Long platformId) {
        return aiPlatformMapper.selectAiPlatformById(platformId);
    }

    /**
     * 查询AI平台配置列表
     *
     * @param aiPlatform AI平台配置
     * @return AI平台配置
     */
    @Override
    public List<AiPlatform> selectAiPlatformList(AiPlatform aiPlatform) {
        return aiPlatformMapper.selectAiPlatformList(aiPlatform);
    }

    /**
     * 根据平台代码查询AI平台配置
     *
     * @param platformCode 平台代码
     * @return AI平台配置
     */
    @Override
    public AiPlatform selectAiPlatformByCode(String platformCode) {
        return aiPlatformMapper.selectAiPlatformByCode(platformCode);
    }

    /**
     * 新增AI平台配置
     *
     * @param aiPlatform AI平台配置
     * @return 结果
     */
    @Override
    public int insertAiPlatform(AiPlatform aiPlatform) {
        return aiPlatformMapper.insertAiPlatform(aiPlatform);
    }

    /**
     * 修改AI平台配置
     *
     * @param aiPlatform AI平台配置
     * @return 结果
     */
    @Override
    public int updateAiPlatform(AiPlatform aiPlatform) {
        return aiPlatformMapper.updateAiPlatform(aiPlatform);
    }

    /**
     * 批量删除AI平台配置
     *
     * @param platformIds 需要删除的AI平台配置ID
     * @return 结果
     */
    @Override
    public int deleteAiPlatformByIds(Long[] platformIds) {
        return aiPlatformMapper.deleteAiPlatformByIds(platformIds);
    }

    /**
     * 删除AI平台配置信息
     *
     * @param platformId AI平台配置ID
     * @return 结果
     */
    @Override
    public int deleteAiPlatformById(Long platformId) {
        return aiPlatformMapper.deleteAiPlatformById(platformId);
    }

    /**
     * 查询所有启用的AI平台
     *
     * @return AI平台配置集合
     */
    @Override
    public List<AiPlatform> selectEnabledPlatforms() {
        return aiPlatformMapper.selectEnabledPlatforms();
    }
}
