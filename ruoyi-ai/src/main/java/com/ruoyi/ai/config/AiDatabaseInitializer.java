package com.ruoyi.ai.config;

import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * AI模块数据库初始化
 * 
 * @author ruoyi
 */
@Component
public class AiDatabaseInitializer implements ApplicationRunner
{
    private static final Logger log = LoggerFactory.getLogger(AiDatabaseInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        log.info("开始初始化AI模块数据库表...");
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement())
        {
            stmt.execute("CREATE TABLE IF NOT EXISTS ai_platform (" +
                "platform_id     INTEGER PRIMARY KEY AUTOINCREMENT," +
                "platform_name   VARCHAR(50)     NOT NULL," +
                "platform_code   VARCHAR(50)     NOT NULL UNIQUE," +
                "api_url         VARCHAR(500)    NOT NULL," +
                "api_key         VARCHAR(500)," +
                "default_model   VARCHAR(100)," +
                "status          CHAR(1)         DEFAULT '0'," +
                "create_by       VARCHAR(64)     DEFAULT ''," +
                "create_time     DATETIME," +
                "update_by       VARCHAR(64)     DEFAULT ''," +
                "update_time     DATETIME," +
                "remark          VARCHAR(500)    DEFAULT ''" +
                ")");
            log.info("创建表 ai_platform 成功");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS ai_chat (" +
                "chat_id         INTEGER PRIMARY KEY AUTOINCREMENT," +
                "platform_id     INTEGER," +
                "platform_name   VARCHAR(50)," +
                "model_name      VARCHAR(100)," +
                "user_message    TEXT," +
                "ai_response     TEXT," +
                "total_tokens    INTEGER," +
                "user_id         INTEGER," +
                "user_name       VARCHAR(64)," +
                "create_time     DATETIME," +
                "remark          VARCHAR(500)    DEFAULT ''" +
                ")");
            log.info("创建表 ai_chat 成功");
            
            try
            {
                stmt.execute("CREATE INDEX IF NOT EXISTS idx_ai_platform_code ON ai_platform(platform_code)");
                stmt.execute("CREATE INDEX IF NOT EXISTS idx_ai_chat_platform_id ON ai_chat(platform_id)");
                stmt.execute("CREATE INDEX IF NOT EXISTS idx_ai_chat_user_id ON ai_chat(user_id)");
                stmt.execute("CREATE INDEX IF NOT EXISTS idx_ai_chat_create_time ON ai_chat(create_time)");
            }
            catch (Exception e)
            {
                log.debug("创建索引失败（可能已存在）: {}", e.getMessage());
            }
            
            try
            {
                int count = 0;
                java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ai_platform");
                if (rs.next())
                {
                    count = rs.getInt(1);
                }
                rs.close();
                
                if (count == 0)
                {
                    stmt.execute("INSERT INTO ai_platform (platform_name, platform_code, api_url, api_key, default_model, status, create_time, remark) " +
                        "VALUES ('通义千问', 'qwen', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', '', 'qwen-turbo', '0', datetime('now'), '阿里云通义千问大模型')");
                    
                    stmt.execute("INSERT INTO ai_platform (platform_name, platform_code, api_url, api_key, default_model, status, create_time, remark) " +
                        "VALUES ('腾讯元宝', 'yuanbao', 'https://hunyuan.tencentcloudapi.com/v1/chat/completions', '', 'hunyuan-lite', '0', datetime('now'), '腾讯混元大模型')");
                    
                    stmt.execute("INSERT INTO ai_platform (platform_name, platform_code, api_url, api_key, default_model, status, create_time, remark) " +
                        "VALUES ('字节豆包', 'doubao', 'https://ark.cn-beijing.volces.com/api/v3/chat/completions', '', 'doubao-pro-4k', '0', datetime('now'), '字节跳动豆包大模型')");
                    
                    log.info("初始化AI平台配置数据成功");
                }
            }
            catch (Exception e)
            {
                log.debug("初始化数据失败（可能已存在）: {}", e.getMessage());
            }
            
            log.info("AI模块数据库表初始化完成");
        }
        catch (Exception e)
        {
            log.error("AI模块数据库表初始化失败: {}", e.getMessage(), e);
        }
    }
}
