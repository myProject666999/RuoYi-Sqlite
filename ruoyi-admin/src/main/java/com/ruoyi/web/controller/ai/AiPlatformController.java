package com.ruoyi.web.controller.ai;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.service.IAiPlatformService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * AI平台配置Controller
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/ai/platform")
public class AiPlatformController extends BaseController
{
    private String prefix = "ai/platform";

    @Autowired
    private IAiPlatformService aiPlatformService;

    @RequiresPermissions("ai:platform:view")
    @GetMapping()
    public String platform()
    {
        return prefix + "/platform";
    }

    @RequiresPermissions("ai:platform:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AiPlatform aiPlatform)
    {
        startPage();
        List<AiPlatform> list = aiPlatformService.selectAiPlatformList(aiPlatform);
        return getDataTable(list);
    }

    @Log(title = "AI平台配置", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ai:platform:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AiPlatform aiPlatform)
    {
        List<AiPlatform> list = aiPlatformService.selectAiPlatformList(aiPlatform);
        ExcelUtil<AiPlatform> util = new ExcelUtil<AiPlatform>(AiPlatform.class);
        return util.exportExcel(list, "AI平台配置数据");
    }

    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @Log(title = "AI平台配置", businessType = BusinessType.INSERT)
    @RequiresPermissions("ai:platform:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated AiPlatform aiPlatform)
    {
        return toAjax(aiPlatformService.insertAiPlatform(aiPlatform));
    }

    @GetMapping("/edit/{platformId}")
    public String edit(@PathVariable("platformId") Long platformId, ModelMap mmap)
    {
        mmap.put("aiPlatform", aiPlatformService.selectAiPlatformById(platformId));
        return prefix + "/edit";
    }

    @Log(title = "AI平台配置", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ai:platform:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated AiPlatform aiPlatform)
    {
        return toAjax(aiPlatformService.updateAiPlatform(aiPlatform));
    }

    @Log(title = "AI平台配置", businessType = BusinessType.DELETE)
    @RequiresPermissions("ai:platform:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long[] platformIds = new Long[ids.split(",").length];
        int index = 0;
        for (String id : ids.split(","))
        {
            platformIds[index++] = Long.parseLong(id);
        }
        return toAjax(aiPlatformService.deleteAiPlatformByIds(platformIds));
    }

    @GetMapping("/listAll")
    @ResponseBody
    public AjaxResult listAll()
    {
        List<AiPlatform> list = aiPlatformService.selectAiPlatformList(new AiPlatform());
        return AjaxResult.success(list);
    }
}
