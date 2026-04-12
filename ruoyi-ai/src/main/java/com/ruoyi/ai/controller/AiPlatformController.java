package com.ruoyi.ai.controller;

import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.service.IAiPlatformService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AI平台配置Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/ai/platform")
public class AiPlatformController extends BaseController {

    @Autowired
    private IAiPlatformService aiPlatformService;

    /**
     * 查询AI平台配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AiPlatform aiPlatform) {
        startPage();
        List<AiPlatform> list = aiPlatformService.selectAiPlatformList(aiPlatform);
        return getDataTable(list);
    }

    /**
     * 导出AI平台配置列表
     */
    @Log(title = "AI平台配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AiPlatform aiPlatform) {
        List<AiPlatform> list = aiPlatformService.selectAiPlatformList(aiPlatform);
        ExcelUtil<AiPlatform> util = new ExcelUtil<>(AiPlatform.class);
        return util.exportExcel(list, "AI平台配置数据");
    }

    /**
     * 获取AI平台配置详细信息
     */
    @GetMapping(value = "/{platformId}")
    public AjaxResult getInfo(@PathVariable("platformId") Long platformId) {
        return AjaxResult.success(aiPlatformService.selectAiPlatformById(platformId));
    }

    /**
     * 新增AI平台配置
     */
    @Log(title = "AI平台配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AiPlatform aiPlatform) {
        return toAjax(aiPlatformService.insertAiPlatform(aiPlatform));
    }

    /**
     * 修改AI平台配置
     */
    @Log(title = "AI平台配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AiPlatform aiPlatform) {
        return toAjax(aiPlatformService.updateAiPlatform(aiPlatform));
    }

    /**
     * 删除AI平台配置
     */
    @Log(title = "AI平台配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{platformIds}")
    public AjaxResult remove(@PathVariable Long[] platformIds) {
        return toAjax(aiPlatformService.deleteAiPlatformByIds(platformIds));
    }
}
