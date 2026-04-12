package com.ruoyi.ai.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * HTTP客户端工具类
 * 
 * @author ruoyi
 */
public class HttpUtil
{
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final int CONNECT_TIMEOUT = 60000;
    private static final int SOCKET_TIMEOUT = 120000;

    public static String doPost(String url, String jsonBody, Map<String, String> headers)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseString = null;

        try
        {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);

            if (headers != null)
            {
                for (Map.Entry<String, String> entry : headers.entrySet())
                {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            if (jsonBody != null)
            {
                StringEntity entity = new StringEntity(jsonBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null)
            {
                responseString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
        }
        catch (Exception e)
        {
            log.error("HTTP POST请求失败: {}", e.getMessage(), e);
        }
        finally
        {
            try
            {
                if (response != null)
                {
                    response.close();
                }
                httpClient.close();
            }
            catch (IOException e)
            {
                log.error("关闭HTTP连接失败: {}", e.getMessage());
            }
        }

        return responseString;
    }

    public static JSONObject doPostJson(String url, JSONObject jsonBody, Map<String, String> headers)
    {
        String response = doPost(url, jsonBody.toJSONString(), headers);
        if (response != null)
        {
            return JSON.parseObject(response);
        }
        return null;
    }
}
