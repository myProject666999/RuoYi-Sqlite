package com.ruoyi.ai.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI对话记录表 ai_chat
 * 
 * @author ruoyi
 */
public class AiChat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "对话ID", cellType = ColumnType.NUMERIC)
    private Long chatId;

    @Excel(name = "平台ID", cellType = ColumnType.NUMERIC)
    private Long platformId;

    @Excel(name = "平台名称")
    private String platformName;

    @Excel(name = "模型名称")
    private String modelName;

    @Excel(name = "用户问题")
    private String userMessage;

    @Excel(name = "AI回答")
    private String aiResponse;

    @Excel(name = "消耗tokens")
    private Long totalTokens;

    @Excel(name = "用户ID")
    private Long userId;

    @Excel(name = "用户名")
    private String userName;

    public Long getChatId()
    {
        return chatId;
    }

    public void setChatId(Long chatId)
    {
        this.chatId = chatId;
    }

    public Long getPlatformId()
    {
        return platformId;
    }

    public void setPlatformId(Long platformId)
    {
        this.platformId = platformId;
    }

    public String getPlatformName()
    {
        return platformName;
    }

    public void setPlatformName(String platformName)
    {
        this.platformName = platformName;
    }

    @Size(min = 0, max = 100, message = "模型名称不能超过100个字符")
    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    @NotBlank(message = "用户问题不能为空")
    public String getUserMessage()
    {
        return userMessage;
    }

    public void setUserMessage(String userMessage)
    {
        this.userMessage = userMessage;
    }

    public String getAiResponse()
    {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse)
    {
        this.aiResponse = aiResponse;
    }

    public Long getTotalTokens()
    {
        return totalTokens;
    }

    public void setTotalTokens(Long totalTokens)
    {
        this.totalTokens = totalTokens;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("chatId", getChatId())
            .append("platformId", getPlatformId())
            .append("platformName", getPlatformName())
            .append("modelName", getModelName())
            .append("userMessage", getUserMessage())
            .append("aiResponse", getAiResponse())
            .append("totalTokens", getTotalTokens())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
