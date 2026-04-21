package com.ruoyi.ai.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * AI聊天请求对象
 *
 * @author ruoyi
 */
public class AiChatRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 平台代码（qianwen/yuanbao/doubao） */
    @NotBlank(message = "平台代码不能为空")
    private String platformCode;

    /** 用户消息 */
    @NotBlank(message = "消息内容不能为空")
    private String message;

    /** 会话ID（用于上下文关联） */
    private String sessionId;

    /** 模型参数：温度（0-2） */
    private Double temperature;

    /** 模型参数：最大token数 */
    private Integer maxTokens;

    /** 是否流式输出 */
    private Boolean stream;

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }
}
