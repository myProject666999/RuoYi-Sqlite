package com.ruoyi.ai.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI平台配置表 ai_platform
 * 
 * @author ruoyi
 */
public class AiPlatform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "平台ID", cellType = ColumnType.NUMERIC)
    private Long platformId;

    @Excel(name = "平台名称")
    private String platformName;

    @Excel(name = "平台编码")
    private String platformCode;

    @Excel(name = "API地址")
    private String apiUrl;

    @Excel(name = "API密钥")
    private String apiKey;

    @Excel(name = "默认模型")
    private String defaultModel;

    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public Long getPlatformId()
    {
        return platformId;
    }

    public void setPlatformId(Long platformId)
    {
        this.platformId = platformId;
    }

    @NotBlank(message = "平台名称不能为空")
    @Size(min = 0, max = 50, message = "平台名称不能超过50个字符")
    public String getPlatformName()
    {
        return platformName;
    }

    public void setPlatformName(String platformName)
    {
        this.platformName = platformName;
    }

    @NotBlank(message = "平台编码不能为空")
    @Size(min = 0, max = 50, message = "平台编码不能超过50个字符")
    public String getPlatformCode()
    {
        return platformCode;
    }

    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }

    @NotBlank(message = "API地址不能为空")
    @Size(min = 0, max = 500, message = "API地址不能超过500个字符")
    public String getApiUrl()
    {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl)
    {
        this.apiUrl = apiUrl;
    }

    @Size(min = 0, max = 500, message = "API密钥不能超过500个字符")
    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    @Size(min = 0, max = 100, message = "默认模型不能超过100个字符")
    public String getDefaultModel()
    {
        return defaultModel;
    }

    public void setDefaultModel(String defaultModel)
    {
        this.defaultModel = defaultModel;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("platformId", getPlatformId())
            .append("platformName", getPlatformName())
            .append("platformCode", getPlatformCode())
            .append("apiUrl", getApiUrl())
            .append("apiKey", getApiKey())
            .append("defaultModel", getDefaultModel())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
