package com.ruoyi.web.controller.ai;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.ai.domain.AiChat;
import com.ruoyi.ai.service.IAiChatService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * AI对话记录Controller
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/ai/chat")
public class AiChatController extends BaseController
{
    private String prefix = "ai/chat";

    @Autowired
    private IAiChatService aiChatService;

    @RequiresPermissions("ai:chat:view")
    @GetMapping()
    public String chat()
    {
        return prefix + "/chat";
    }

    @RequiresPermissions("ai:chat:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AiChat aiChat)
    {
        startPage();
        List<AiChat> list = aiChatService.selectAiChatList(aiChat);
        return getDataTable(list);
    }

    @Log(title = "AI对话记录", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ai:chat:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AiChat aiChat)
    {
        List<AiChat> list = aiChatService.selectAiChatList(aiChat);
        ExcelUtil<AiChat> util = new ExcelUtil<AiChat>(AiChat.class);
        return util.exportExcel(list, "AI对话记录数据");
    }

    @GetMapping("/detail/{chatId}")
    public String detail(@PathVariable("chatId") Long chatId, ModelMap mmap)
    {
        mmap.put("aiChat", aiChatService.selectAiChatById(chatId));
        return prefix + "/detail";
    }

    @Log(title = "AI对话记录", businessType = BusinessType.DELETE)
    @RequiresPermissions("ai:chat:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long[] chatIds = new Long[ids.split(",").length];
        int index = 0;
        for (String id : ids.split(","))
        {
            chatIds[index++] = Long.parseLong(id);
        }
        return toAjax(aiChatService.deleteAiChatByIds(chatIds));
    }
}
