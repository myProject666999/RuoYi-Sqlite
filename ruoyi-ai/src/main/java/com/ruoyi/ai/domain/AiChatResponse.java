package com.ruoyi.ai.domain;

import java.io.Serializable;

/**
 * AI聊天响应对象
 *
 * @author ruoyi
 */
public class AiChatResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 是否成功 */
    private boolean success;

    /** 响应消息 */
    private String message;

    /** 平台代码 */
    private String platformCode;

    /** 平台名称 */
    private String platformName;

    /** 会话ID */
    private String sessionId;

    /** AI回复内容 */
    private String content;

    /** 请求tokens */
    private Integer promptTokens;

    /** 响应tokens */
    private Integer completionTokens;

    /** 总tokens */
    private Integer totalTokens;

    /** 响应时间(ms) */
    private Long responseTime;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(Integer promptTokens) {
        this.promptTokens = promptTokens;
    }

    public Integer getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(Integer completionTokens) {
        this.completionTokens = completionTokens;
    }

    public Integer getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public static AiChatResponse success(String content) {
        AiChatResponse response = new AiChatResponse();
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }

    public static AiChatResponse error(String message) {
        AiChatResponse response = new AiChatResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
