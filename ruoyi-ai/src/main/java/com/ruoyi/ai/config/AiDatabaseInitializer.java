package com.ruoyi.ai.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * AI模块数据库初始化器
 *
 * @author ruoyi
 */
@Component
public class AiDatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AiDatabaseInitializer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 检查表是否存在
            boolean tablesExist = checkTablesExist();
            if (!tablesExist) {
                log.info("AI模块数据库表不存在，开始初始化...");
                executeInitSql();
                log.info("AI模块数据库初始化完成");
            } else {
                log.info("AI模块数据库表已存在，跳过初始化");
            }
        } catch (Exception e) {
            log.error("AI模块数据库初始化失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 检查AI模块表是否存在
     */
    private boolean checkTablesExist() {
        try {
            jdbcTemplate.queryForObject(
                    "SELECT 1 FROM ai_platform LIMIT 1",
                    Integer.class
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 执行初始化SQL脚本
     */
    private void executeInitSql() {
        try {
            Resource resource = new ClassPathResource("sql/ai_init.sql");
            String sql = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            ).lines().collect(Collectors.joining("\n"));

            // 按分号分割SQL语句
            String[] statements = sql.split(";");
            for (String statement : statements) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty() && !trimmed.startsWith("--")) {
                    try {
                        jdbcTemplate.execute(trimmed);
                    } catch (Exception e) {
                        log.warn("执行SQL语句失败: {}, 错误: {}", trimmed.substring(0, Math.min(50, trimmed.length())), e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("执行初始化SQL失败", e);
        }
    }
}
