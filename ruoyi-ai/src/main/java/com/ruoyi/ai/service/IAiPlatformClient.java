package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatform;

/**
 * AI平台客户端接口
 *
 * @author ruoyi
 */
public interface IAiPlatformClient {

    /**
     * 获取平台代码
     *
     * @return 平台代码
     */
    String getPlatformCode();

    /**
     * 获取平台名称
     *
     * @return 平台名称
     */
    String getPlatformName();

    /**
     * 发送聊天请求
     *
     * @param platform AI平台配置
     * @param request  聊天请求
     * @return 聊天响应
     */
    AiChatResponse chat(AiPlatform platform, AiChatRequest request);

    /**
     * 检查平台是否可用
     *
     * @param platform AI平台配置
     * @return 是否可用
     */
    boolean checkAvailable(AiPlatform platform);
}
