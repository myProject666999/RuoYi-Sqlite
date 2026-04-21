package com.ruoyi.ai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * AI对话记录表 ai_chat_history
 *
 * @author ruoyi
 */
public class AiChatHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    @Excel(name = "记录ID", cellType = Excel.ColumnType.NUMERIC)
    private Long historyId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 平台ID */
    @Excel(name = "平台ID")
    private Long platformId;

    /** 平台名称 */
    @Excel(name = "平台名称")
    private String platformName;

    /** 会话ID */
    @Excel(name = "会话ID")
    private String sessionId;

    /** 用户提问 */
    @Excel(name = "用户提问")
    private String userMessage;

    /** AI回复 */
    @Excel(name = "AI回复")
    private String aiResponse;

    /** 请求tokens */
    @Excel(name = "请求tokens")
    private Integer promptTokens;

    /** 响应tokens */
    @Excel(name = "响应tokens")
    private Integer completionTokens;

    /** 总tokens */
    @Excel(name = "总tokens")
    private Integer totalTokens;

    /** 响应时间(ms) */
    @Excel(name = "响应时间")
    private Long responseTime;

    /** 状态（0成功 1失败） */
    @Excel(name = "状态", readConverterExp = "0=成功,1=失败")
    private String status;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errorMsg;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
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

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("historyId", getHistoryId())
                .append("userId", getUserId())
                .append("platformId", getPlatformId())
                .append("platformName", getPlatformName())
                .append("sessionId", getSessionId())
                .append("userMessage", getUserMessage())
                .append("aiResponse", getAiResponse())
                .append("promptTokens", getPromptTokens())
                .append("completionTokens", getCompletionTokens())
                .append("totalTokens", getTotalTokens())
                .append("responseTime", getResponseTime())
                .append("status", getStatus())
                .append("errorMsg", getErrorMsg())
                .append("createTime", getCreateTime())
                .toString();
    }
}
