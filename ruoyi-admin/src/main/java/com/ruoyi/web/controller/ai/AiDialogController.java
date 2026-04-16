package com.ruoyi.web.controller.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AI对话页面Controller
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/ai/dialog")
public class AiDialogController
{
    @GetMapping()
    public String dialog()
    {
        return "ai/chat/dialog";
    }
}
