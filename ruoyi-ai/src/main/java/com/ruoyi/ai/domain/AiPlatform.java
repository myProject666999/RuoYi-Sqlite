package com.ruoyi.ai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * AI平台配置表 ai_platform
 *
 * @author ruoyi
 */
public class AiPlatform extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 平台ID */
    @Excel(name = "平台ID", cellType = Excel.ColumnType.NUMERIC)
    private Long platformId;

    /** 平台名称 */
    @Excel(name = "平台名称")
    private String platformName;

    /** 平台代码 */
    @Excel(name = "平台代码")
    private String platformCode;

    /** API基础URL */
    @Excel(name = "API基础URL")
    private String apiBaseUrl;

    /** API密钥 */
    private String apiKey;

    /** 模型名称 */
    @Excel(name = "模型名称")
    private String modelName;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortOrder;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    @NotBlank(message = "平台名称不能为空")
    @Size(min = 0, max = 100, message = "平台名称不能超过100个字符")
    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @NotBlank(message = "平台代码不能为空")
    @Size(min = 0, max = 50, message = "平台代码不能超过50个字符")
    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @NotBlank(message = "API基础URL不能为空")
    @Size(min = 0, max = 500, message = "API基础URL不能超过500个字符")
    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @NotBlank(message = "模型名称不能为空")
    @Size(min = 0, max = 100, message = "模型名称不能超过100个字符")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("platformId", getPlatformId())
                .append("platformName", getPlatformName())
                .append("platformCode", getPlatformCode())
                .append("apiBaseUrl", getApiBaseUrl())
                .append("modelName", getModelName())
                .append("status", getStatus())
                .append("sortOrder", getSortOrder())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
