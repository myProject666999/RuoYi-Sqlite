package com.ruoyi.web.controller.ai;

import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiChatRecord;
import com.ruoyi.ai.domain.AiPlatformConfig;
import com.ruoyi.ai.service.IAggregatedAiService;
import com.ruoyi.ai.service.IAiChatRecordService;
import com.ruoyi.ai.service.IAiPlatformConfigService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ai")
public class AiController extends BaseController
{
    @Autowired
    private IAggregatedAiService aggregatedAiService;

    @Autowired
    private IAiPlatformConfigService platformConfigService;

    @Autowired
    private IAiChatRecordService chatRecordService;

    @PostMapping("/chat")
    public AjaxResult chat(@Validated @RequestBody AiChatRequest request)
    {
        Long userId = ShiroUtils.getUserId();
        AiChatResponse response = aggregatedAiService.chat(request, userId);
        if (response.isSuccess())
        {
            return AjaxResult.success(response);
        }
        else
        {
            return AjaxResult.error(response.getErrorMsg());
        }
    }

    @PostMapping("/chat/simple")
    public AjaxResult chatSimple(
            @RequestParam String platformCode,
            @RequestParam String prompt)
    {
        Long userId = ShiroUtils.getUserId();
        AiChatResponse response = aggregatedAiService.chat(platformCode, prompt, userId);
        if (response.isSuccess())
        {
            return AjaxResult.success(response);
        }
        else
        {
            return AjaxResult.error(response.getErrorMsg());
        }
    }

    @RequiresPermissions("ai:platform:list")
    @GetMapping("/platform/list")
    public TableDataInfo listPlatform(AiPlatformConfig aiPlatformConfig)
    {
        startPage();
        List<AiPlatformConfig> list = platformConfigService.selectAiPlatformConfigList(aiPlatformConfig);
        return getDataTable(list);
    }

    @RequiresPermissions("ai:platform:export")
    @Log(title = "AI平台配置", businessType = BusinessType.EXPORT)
    @PostMapping("/platform/export")
    public void exportPlatform(HttpServletResponse response, AiPlatformConfig aiPlatformConfig)
    {
        List<AiPlatformConfig> list = platformConfigService.selectAiPlatformConfigList(aiPlatformConfig);
        ExcelUtil<AiPlatformConfig> util = new ExcelUtil<>(AiPlatformConfig.class);
        util.exportExcel(response, list, "AI平台配置数据");
    }

    @RequiresPermissions("ai:platform:query")
    @GetMapping(value = "/platform/{configId}")
    public AjaxResult getPlatformInfo(@PathVariable Long configId)
    {
        return AjaxResult.success(platformConfigService.selectAiPlatformConfigByConfigId(configId));
    }

    @RequiresPermissions("ai:platform:add")
    @Log(title = "AI平台配置", businessType = BusinessType.INSERT)
    @PostMapping("/platform")
    public AjaxResult addPlatform(@Validated @RequestBody AiPlatformConfig aiPlatformConfig)
    {
        aiPlatformConfig.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(platformConfigService.insertAiPlatformConfig(aiPlatformConfig));
    }

    @RequiresPermissions("ai:platform:edit")
    @Log(title = "AI平台配置", businessType = BusinessType.UPDATE)
    @PutMapping("/platform")
    public AjaxResult editPlatform(@Validated @RequestBody AiPlatformConfig aiPlatformConfig)
    {
        aiPlatformConfig.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(platformConfigService.updateAiPlatformConfig(aiPlatformConfig));
    }

    @RequiresPermissions("ai:platform:remove")
    @Log(title = "AI平台配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/platform/{configIds}")
    public AjaxResult removePlatform(@PathVariable Long[] configIds)
    {
        return toAjax(platformConfigService.deleteAiPlatformConfigByConfigIds(configIds));
    }

    @RequiresPermissions("ai:record:list")
    @GetMapping("/record/list")
    public TableDataInfo listRecord(AiChatRecord aiChatRecord)
    {
        startPage();
        List<AiChatRecord> list = chatRecordService.selectAiChatRecordList(aiChatRecord);
        return getDataTable(list);
    }

    @RequiresPermissions("ai:record:export")
    @Log(title = "AI聊天记录", businessType = BusinessType.EXPORT)
    @PostMapping("/record/export")
    public void exportRecord(HttpServletResponse response, AiChatRecord aiChatRecord)
    {
        List<AiChatRecord> list = chatRecordService.selectAiChatRecordList(aiChatRecord);
        ExcelUtil<AiChatRecord> util = new ExcelUtil<>(AiChatRecord.class);
        util.exportExcel(response, list, "AI聊天记录数据");
    }

    @RequiresPermissions("ai:record:remove")
    @Log(title = "AI聊天记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/record/{recordIds}")
    public AjaxResult removeRecord(@PathVariable Long[] recordIds)
    {
        return toAjax(chatRecordService.deleteAiChatRecordByRecordIds(recordIds));
    }
}
