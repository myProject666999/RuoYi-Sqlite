-- AI模块数据库初始化脚本 (SQLite)
-- 创建AI平台配置表
CREATE TABLE IF NOT EXISTS ai_platform (
    platform_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_name VARCHAR(100) NOT NULL COMMENT '平台名称',
    platform_code VARCHAR(50) NOT NULL COMMENT '平台代码',
    api_base_url VARCHAR(500) NOT NULL COMMENT 'API基础URL',
    api_key VARCHAR(500) COMMENT 'API密钥',
    model_name VARCHAR(100) NOT NULL COMMENT '模型名称',
    status CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
    sort_order INTEGER DEFAULT 0 COMMENT '排序',
    remark VARCHAR(500) COMMENT '备注',
    create_by VARCHAR(64) COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间'
);

-- 创建AI对话记录表
CREATE TABLE IF NOT EXISTS ai_chat_history (
    history_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id BIGINT COMMENT '用户ID',
    platform_id BIGINT COMMENT '平台ID',
    platform_name VARCHAR(100) COMMENT '平台名称',
    session_id VARCHAR(64) COMMENT '会话ID',
    user_message TEXT COMMENT '用户提问',
    ai_response TEXT COMMENT 'AI回复',
    prompt_tokens INTEGER COMMENT '请求tokens',
    completion_tokens INTEGER COMMENT '响应tokens',
    total_tokens INTEGER COMMENT '总tokens',
    response_time BIGINT COMMENT '响应时间(ms)',
    status CHAR(1) DEFAULT '0' COMMENT '状态（0成功 1失败）',
    error_msg TEXT COMMENT '错误信息',
    create_time DATETIME COMMENT '创建时间'
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
