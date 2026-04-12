-- =====================================================
-- 安全更新AI表结构 - 仅创建不存在的表，不删除原有数据
-- =====================================================

-- 1. 创建 ai_platform_config 表（如果不存在）
CREATE TABLE IF NOT EXISTS ai_platform_config (
    config_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_name VARCHAR(100) NOT NULL,
    platform_code VARCHAR(50) NOT NULL UNIQUE,
    api_key VARCHAR(500) NOT NULL,
    api_secret VARCHAR(500),
    api_url VARCHAR(500) NOT NULL,
    model VARCHAR(100),
    temperature DECIMAL(3,2) DEFAULT 0.7,
    max_tokens INTEGER DEFAULT 2000,
    status CHAR(1) DEFAULT '0',
    remark VARCHAR(500),
    create_by VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_by VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP
);

-- 2. 仅当 ai_platform_config 表为空时，插入初始配置数据
INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
SELECT '通义千问', 'qwen', 'your-qwen-api-key', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', 'qwen-turbo', '1', '请配置真实的API密钥'
WHERE NOT EXISTS (SELECT 1 FROM ai_platform_config WHERE platform_code = 'qwen');

INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
SELECT '字节跳动-豆包', 'doubao', 'your-doubao-api-key', 'https://ark.cn-beijing.volces.com/api/v3/chat/completions', 'doubao-pro-4k', '1', '请配置真实的API密钥'
WHERE NOT EXISTS (SELECT 1 FROM ai_platform_config WHERE platform_code = 'doubao');

INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
SELECT '百度-元宝', 'yuanbao', 'your-yuanbao-api-key', 'https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-4.0-turbo-8k', 'ernie-4.0-turbo', '1', '请配置真实的API密钥'
WHERE NOT EXISTS (SELECT 1 FROM ai_platform_config WHERE platform_code = 'yuanbao');

-- 3. 创建 ai_chat_record 表（如果不存在）
CREATE TABLE IF NOT EXISTS ai_chat_record (
    record_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_code VARCHAR(50) NOT NULL,
    model VARCHAR(100),
    user_id BIGINT,
    prompt TEXT NOT NULL,
    response TEXT,
    prompt_tokens INTEGER,
    response_tokens INTEGER,
    total_tokens INTEGER,
    exec_time INTEGER,
    error_msg VARCHAR(500),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. 验证结果
SELECT 'ai_platform_config 表记录数:' as info, COUNT(*) as count FROM ai_platform_config
UNION ALL
SELECT 'ai_chat_record 表记录数:' as info, COUNT(*) as count FROM ai_chat_record;
