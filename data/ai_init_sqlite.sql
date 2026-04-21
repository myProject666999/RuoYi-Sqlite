-- AI模块数据库初始化脚本 (SQLite兼容版本)
-- 创建AI平台配置表
CREATE TABLE IF NOT EXISTS ai_platform (
    platform_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_name VARCHAR(100) NOT NULL,
    platform_code VARCHAR(50) NOT NULL,
    api_base_url VARCHAR(500) NOT NULL,
    api_key VARCHAR(500),
    model_name VARCHAR(100) NOT NULL,
    status CHAR(1) DEFAULT '0',
    sort_order INTEGER DEFAULT 0,
    remark VARCHAR(500),
    create_by VARCHAR(64),
    create_time DATETIME,
    update_by VARCHAR(64),
    update_time DATETIME
);

-- 创建AI对话记录表
CREATE TABLE IF NOT EXISTS ai_chat_history (
    history_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id BIGINT,
    platform_id BIGINT,
    platform_name VARCHAR(100),
    session_id VARCHAR(64),
    user_message TEXT,
    ai_response TEXT,
    prompt_tokens INTEGER,
    completion_tokens INTEGER,
    total_tokens INTEGER,
    response_time BIGINT,
    status CHAR(1) DEFAULT '0',
    error_msg TEXT,
    create_time DATETIME
);

-- 创建索引
CREATE INDEX IF NOT EXISTS idx_ai_platform_code ON ai_platform(platform_code);
CREATE INDEX IF NOT EXISTS idx_ai_platform_status ON ai_platform(status);
CREATE INDEX IF NOT EXISTS idx_ai_chat_history_session ON ai_chat_history(session_id);
CREATE INDEX IF NOT EXISTS idx_ai_chat_history_platform ON ai_chat_history(platform_id);
CREATE INDEX IF NOT EXISTS idx_ai_chat_history_user ON ai_chat_history(user_id);
CREATE INDEX IF NOT EXISTS idx_ai_chat_history_time ON ai_chat_history(create_time);

-- 插入默认AI平台配置（示例数据，需要替换为真实的API密钥）
-- 通义千问
INSERT OR IGNORE INTO ai_platform (platform_id, platform_name, platform_code, api_base_url, api_key, model_name, status, sort_order, remark, create_time)
VALUES (1, '通义千问', 'qianwen', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', 'your-qianwen-api-key', 'qwen-turbo', '0', 1, '阿里云通义千问大模型', datetime('now','localtime'));

-- 腾讯元宝 (使用混元大模型API)
INSERT OR IGNORE INTO ai_platform (platform_id, platform_name, platform_code, api_base_url, api_key, model_name, status, sort_order, remark, create_time)
VALUES (2, '腾讯元宝', 'yuanbao', 'https://hunyuan.tencentcloudapi.com/v1/chat/completions', 'your-yuanbao-api-key', 'hunyuan-lite', '0', 2, '腾讯混元大模型', datetime('now','localtime'));

-- 字节豆包
INSERT OR IGNORE INTO ai_platform (platform_id, platform_name, platform_code, api_base_url, api_key, model_name, status, sort_order, remark, create_time)
VALUES (3, '字节豆包', 'doubao', 'https://ark.cn-beijing.volces.com/api/v3/chat/completions', 'your-doubao-api-key', 'doubao-lite-4k', '0', 3, '字节跳动豆包大模型', datetime('now','localtime'));
