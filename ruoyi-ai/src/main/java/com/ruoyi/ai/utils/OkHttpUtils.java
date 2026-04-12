package com.ruoyi.ai.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils
{
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String post(String url, String jsonBody, Map<String, String> headers) throws IOException
    {
        Request.Builder builder = new Request.Builder()
                .url(url);

        if (headers != null)
        {
            for (Map.Entry<String, String> entry : headers.entrySet())
            {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = builder.post(body).build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful())
            {
                throw new IOException("Unexpected code " + response + ", body: " + (response.body() != null ? response.body().string() : ""));
            }
            return response.body() != null ? response.body().string() : "";
        }
    }

    public static String post(String url, String jsonBody, String token) throws IOException
    {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute())
        {
            if (!response.isSuccessful())
            {
                throw new IOException("Unexpected code " + response + ", body: " + (response.body() != null ? response.body().string() : ""));
            }
            return response.body() != null ? response.body().string() : "";
        }
    }
}
