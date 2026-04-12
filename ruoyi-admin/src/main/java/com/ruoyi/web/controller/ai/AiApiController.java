package com.ruoyi.web.controller.ai;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.ai.domain.AiChat;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.service.AiService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * AI统一调用API接口
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/ai")
public class AiApiController extends BaseController
{
    @Autowired
    private AiService aiService;

    @PostMapping("/chat")
    public AjaxResult chat(@RequestBody ChatRequest request)
    {
        if (request.getMessage() == null || request.getMessage().isEmpty())
        {
            return AjaxResult.error("消息内容不能为空");
        }
        
        AiChat chat = aiService.chat(request.getPlatformCode(), request.getModel(), request.getMessage());
        return AjaxResult.success(chat);
    }

    @PostMapping("/chat/{platformId}")
    public AjaxResult chatByPlatformId(@PathVariable("platformId") Long platformId, @RequestBody ChatRequest request)
    {
        if (request.getMessage() == null || request.getMessage().isEmpty())
        {
            return AjaxResult.error("消息内容不能为空");
        }
        
        AiChat chat = aiService.chatByPlatformId(platformId, request.getModel(), request.getMessage());
        return AjaxResult.success(chat);
    }

    @GetMapping("/platforms")
    public AjaxResult getAvailablePlatforms()
    {
        List<AiPlatform> platforms = aiService.getAvailablePlatforms();
        return AjaxResult.success(platforms);
    }

    public static class ChatRequest
    {
        private String platformCode;
        private String model;
        private String message;

        public String getPlatformCode()
        {
            return platformCode;
        }

        public void setPlatformCode(String platformCode)
        {
            this.platformCode = platformCode;
        }

        public String getModel()
        {
            return model;
        }

        public void setModel(String model)
        {
            this.model = model;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }
    }
}
