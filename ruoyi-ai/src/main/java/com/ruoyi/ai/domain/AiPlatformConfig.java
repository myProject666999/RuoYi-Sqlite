package com.ruoyi.ai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

public class AiPlatformConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long configId;

    @Excel(name = "平台名称")
    private String platformName;

    @Excel(name = "平台编码")
    private String platformCode;

    @Excel(name = "API密钥")
    private String apiKey;

    private String apiSecret;

    @Excel(name = "API地址")
    private String apiUrl;

    @Excel(name = "默认模型")
    private String model;

    @Excel(name = "温度参数")
    private BigDecimal temperature;

    @Excel(name = "最大token数")
    private Integer maxTokens;

    @Excel(name = "状态", readConverterExp = "0=启用,1=禁用")
    private String status;

    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    public Long getConfigId()
    {
        return configId;
    }

    public void setPlatformName(String platformName)
    {
        this.platformName = platformName;
    }

    public String getPlatformName()
    {
        return platformName;
    }

    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }

    public String getPlatformCode()
    {
        return platformCode;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiUrl(String apiUrl)
    {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl()
    {
        return apiUrl;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel()
    {
        return model;
    }

    public void setTemperature(BigDecimal temperature)
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature()
    {
        return temperature;
    }

    public void setMaxTokens(Integer maxTokens)
    {
        this.maxTokens = maxTokens;
    }

    public Integer getMaxTokens()
    {
        return maxTokens;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("configId", getConfigId())
                .append("platformName", getPlatformName())
                .append("platformCode", getPlatformCode())
                .append("apiKey", getApiKey())
                .append("apiSecret", getApiSecret())
                .append("apiUrl", getApiUrl())
                .append("model", getModel())
                .append("temperature", getTemperature())
                .append("maxTokens", getMaxTokens())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
