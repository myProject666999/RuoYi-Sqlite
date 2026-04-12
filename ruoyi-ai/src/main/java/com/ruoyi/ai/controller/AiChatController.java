package com.ruoyi.ai.controller;

import com.ruoyi.ai.domain.AiChatHistory;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.service.IAiChatService;
import com.ruoyi.ai.service.IAiPlatformService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AI对话Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/ai/chat")
public class AiChatController extends BaseController {

    @Autowired
    private IAiChatService aiChatService;

    @Autowired
    private IAiPlatformService aiPlatformService;

    /**
     * 发送聊天消息（统一API接口）
     */
    @PostMapping("/send")
    @Log(title = "AI对话", businessType = BusinessType.INSERT)
    public AjaxResult chat(@Validated @RequestBody AiChatRequest request) {
        AiChatResponse response = aiChatService.chat(request);
        if (response.isSuccess()) {
            return AjaxResult.success(response);
        } else {
            return AjaxResult.error(response.getMessage());
        }
    }

    /**
     * 获取启用的AI平台列表
     */
    @GetMapping("/platforms")
    public AjaxResult getEnabledPlatforms() {
        List<AiPlatform> platforms = aiPlatformService.selectEnabledPlatforms();
        return AjaxResult.success(platforms);
    }

    /**
     * 查询AI对话记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AiChatHistory aiChatHistory) {
        startPage();
        List<AiChatHistory> list = aiChatService.selectAiChatHistoryList(aiChatHistory);
        return getDataTable(list);
    }

    /**
     * 根据会话ID查询对话记录
     */
    @GetMapping("/session/{sessionId}")
    public AjaxResult getBySessionId(@PathVariable String sessionId) {
        List<AiChatHistory> list = aiChatService.selectAiChatHistoryBySessionId(sessionId);
        return AjaxResult.success(list);
    }

    /**
     * 删除AI对话记录
     */
    @Log(title = "AI对话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{historyIds}")
    public AjaxResult remove(@PathVariable Long[] historyIds) {
        return toAjax(aiChatService.deleteAiChatHistoryByIds(historyIds));
    }

    /**
     * 根据会话ID删除对话记录
     */
    @Log(title = "AI对话", businessType = BusinessType.DELETE)
    @DeleteMapping("/session/{sessionId}")
    public AjaxResult removeBySessionId(@PathVariable String sessionId) {
        return toAjax(aiChatService.deleteAiChatHistoryBySessionId(sessionId));
    }
}
