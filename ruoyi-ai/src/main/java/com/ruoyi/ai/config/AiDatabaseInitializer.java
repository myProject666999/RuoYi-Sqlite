package com.ruoyi.ai.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class AiDatabaseInitializer implements ApplicationRunner
{
    private static final Logger log = LoggerFactory.getLogger(AiDatabaseInitializer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args)
    {
        try
        {
            ClassPathResource resource = new ClassPathResource("sql/ai_tables.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                sqlBuilder.append(line).append("\n");
            }
            reader.close();

            String[] sqlStatements = sqlBuilder.toString().split(";");
            for (String sql : sqlStatements)
            {
                sql = sql.trim();
                if (!sql.isEmpty())
                {
                    try
                    {
                        jdbcTemplate.execute(sql);
                    }
                    catch (Exception e)
                    {
                        if (!e.getMessage().contains("already exists"))
                        {
                            log.warn("执行SQL失败: {}", e.getMessage());
                        }
                    }
                }
            }
            log.info("AI数据库表初始化完成");
        }
        catch (Exception e)
        {
            log.error("AI数据库表初始化失败", e);
        }
    }
}
