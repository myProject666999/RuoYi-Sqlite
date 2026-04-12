package com.ruoyi.ai.factory;

import com.ruoyi.ai.service.IAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AiServiceFactory
{
    private final Map<String, IAiService> serviceMap = new HashMap<>();

    @Autowired
    public AiServiceFactory(List<IAiService> aiServices)
    {
        for (IAiService service : aiServices)
        {
            serviceMap.put(service.getPlatformCode(), service);
        }
    }

    public IAiService getService(String platformCode)
    {
        return serviceMap.get(platformCode);
    }

    public Map<String, IAiService> getAllServices()
    {
        return serviceMap;
    }
}
