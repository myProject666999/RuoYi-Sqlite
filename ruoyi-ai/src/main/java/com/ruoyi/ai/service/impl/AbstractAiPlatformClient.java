package com.ruoyi.ai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ai.domain.AiChatRequest;
import com.ruoyi.ai.domain.AiChatResponse;
import com.ruoyi.ai.domain.AiPlatform;
import com.ruoyi.ai.service.IAiPlatformClient;
import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * AI平台客户端抽象类
 *
 * @author ruoyi
 */
public abstract class AbstractAiPlatformClient implements IAiPlatformClient {

    protected static final Logger log = LoggerFactory.getLogger(AbstractAiPlatformClient.class);

    /**
     * 发送HTTP POST请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param body    请求体
     * @return 响应字符串
     */
    protected String post(String url, java.util.Map<String, String> headers, String body) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if (headers != null) {
            for (java.util.Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置请求体
        httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));

        try {
            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("HTTP请求失败: {}", e.getMessage());
            throw new RuntimeException("HTTP请求失败: " + e.getMessage());
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("关闭HTTP客户端失败: {}", e.getMessage());
            }
        }
    }

    /**
     * 构建请求头
     *
     * @param apiKey API密钥
     * @return 请求头
     */
    protected abstract java.util.Map<String, String> buildHeaders(String apiKey);

    /**
     * 构建请求体
     *
     * @param platform AI平台配置
     * @param request  聊天请求
     * @return 请求体JSON字符串
     */
    protected abstract String buildRequestBody(AiPlatform platform, AiChatRequest request);

    /**
     * 解析响应
     *
     * @param responseBody 响应体
     * @return 聊天响应
     */
    protected abstract AiChatResponse parseResponse(String responseBody);

    @Override
    public AiChatResponse chat(AiPlatform platform, AiChatRequest request) {
        long startTime = System.currentTimeMillis();
        try {
            String url = platform.getApiBaseUrl();
            java.util.Map<String, String> headers = buildHeaders(platform.getApiKey());
            String body = buildRequestBody(platform, request);

            log.debug("发送请求到 {}: {}", getPlatformName(), body);
            String responseBody = post(url, headers, body);
            log.debug("收到响应: {}", responseBody);

            AiChatResponse response = parseResponse(responseBody);
            response.setPlatformCode(getPlatformCode());
            response.setPlatformName(getPlatformName());
            response.setResponseTime(System.currentTimeMillis() - startTime);
            response.setSuccess(true);

            return response;
        } catch (Exception e) {
            log.error("{} 请求失败: {}", getPlatformName(), e.getMessage());
            AiChatResponse response = new AiChatResponse();
            response.setPlatformCode(getPlatformCode());
            response.setPlatformName(getPlatformName());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setResponseTime(System.currentTimeMillis() - startTime);
            return response;
        }
    }

    @Override
    public boolean checkAvailable(AiPlatform platform) {
        try {
            AiChatRequest request = new AiChatRequest();
            request.setMessage("Hello");
            request.setMaxTokens(5);
            AiChatResponse response = chat(platform, request);
            return response.isSuccess();
        } catch (Exception e) {
            return false;
        }
    }
}
