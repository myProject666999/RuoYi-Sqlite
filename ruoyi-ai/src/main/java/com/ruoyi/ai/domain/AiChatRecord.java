package com.ruoyi.ai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class AiChatRecord
{
    private static final long serialVersionUID = 1L;

    private Long recordId;

    @Excel(name = "平台编码")
    private String platformCode;

    @Excel(name = "使用模型")
    private String model;

    @Excel(name = "用户ID")
    private Long userId;

    @Excel(name = "用户提问")
    private String prompt;

    @Excel(name = "AI回复")
    private String response;

    @Excel(name = "提问token数")
    private Integer promptTokens;

    @Excel(name = "回复token数")
    private Integer responseTokens;

    @Excel(name = "总token数")
    private Integer totalTokens;

    @Excel(name = "执行时间")
    private Integer execTime;

    @Excel(name = "错误信息")
    private String errorMsg;

    private Date createTime;

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }

    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }

    public String getPlatformCode()
    {
        return platformCode;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel()
    {
        return model;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
    }

    public String getPrompt()
    {
        return prompt;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getResponse()
    {
        return response;
    }

    public void setPromptTokens(Integer promptTokens)
    {
        this.promptTokens = promptTokens;
    }

    public Integer getPromptTokens()
    {
        return promptTokens;
    }

    public void setResponseTokens(Integer responseTokens)
    {
        this.responseTokens = responseTokens;
    }

    public Integer getResponseTokens()
    {
        return responseTokens;
    }

    public void setTotalTokens(Integer totalTokens)
    {
        this.totalTokens = totalTokens;
    }

    public Integer getTotalTokens()
    {
        return totalTokens;
    }

    public void setExecTime(Integer execTime)
    {
        this.execTime = execTime;
    }

    public Integer getExecTime()
    {
        return execTime;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recordId", getRecordId())
                .append("platformCode", getPlatformCode())
                .append("model", getModel())
                .append("userId", getUserId())
                .append("prompt", getPrompt())
                .append("response", getResponse())
                .append("promptTokens", getPromptTokens())
                .append("responseTokens", getResponseTokens())
                .append("totalTokens", getTotalTokens())
                .append("execTime", getExecTime())
                .append("errorMsg", getErrorMsg())
                .append("createTime", getCreateTime())
                .toString();
    }
}
