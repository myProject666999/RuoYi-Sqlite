package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiPlatform;

import java.util.List;

/**
 * AI平台配置Service接口
 *
 * @author ruoyi
 */
public interface IAiPlatformService {

    /**
     * 查询AI平台配置
     *
     * @param platformId AI平台配置ID
     * @return AI平台配置
     */
    public AiPlatform selectAiPlatformById(Long platformId);

    /**
     * 查询AI平台配置列表
     *
     * @param aiPlatform AI平台配置
     * @return AI平台配置集合
     */
    public List<AiPlatform> selectAiPlatformList(AiPlatform aiPlatform);

    /**
     * 根据平台代码查询AI平台配置
     *
     * @param platformCode 平台代码
     * @return AI平台配置
     */
    public AiPlatform selectAiPlatformByCode(String platformCode);

    /**
     * 新增AI平台配置
     *
     * @param aiPlatform AI平台配置
     * @return 结果
     */
    public int insertAiPlatform(AiPlatform aiPlatform);

    /**
     * 修改AI平台配置
     *
     * @param aiPlatform AI平台配置
     * @return 结果
     */
    public int updateAiPlatform(AiPlatform aiPlatform);

    /**
     * 批量删除AI平台配置
     *
     * @param platformIds 需要删除的AI平台配置ID
     * @return 结果
     */
    public int deleteAiPlatformByIds(Long[] platformIds);

    /**
     * 删除AI平台配置信息
     *
     * @param platformId AI平台配置ID
     * @return 结果
     */
    public int deleteAiPlatformById(Long platformId);

    /**
     * 查询所有启用的AI平台
     *
     * @return AI平台配置集合
     */
    public List<AiPlatform> selectEnabledPlatforms();
}
