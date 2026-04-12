-- AI平台配置表
CREATE TABLE IF NOT EXISTS ai_platform_config (
    config_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_name VARCHAR(100) NOT NULL COMMENT '平台名称',
    platform_code VARCHAR(50) NOT NULL UNIQUE COMMENT '平台编码: qwen, yuanbao, doubao',
    api_key VARCHAR(500) NOT NULL COMMENT 'API密钥',
    api_secret VARCHAR(500) COMMENT 'API密钥(备用)',
    api_url VARCHAR(500) NOT NULL COMMENT 'API请求地址',
    model VARCHAR(100) COMMENT '默认模型',
    temperature DECIMAL(3,2) DEFAULT 0.7 COMMENT '温度参数',
    max_tokens INTEGER DEFAULT 2000 COMMENT '最大token数',
    status CHAR(1) DEFAULT '0' COMMENT '状态: 0启用, 1禁用',
    remark VARCHAR(500) COMMENT '备注',
    create_by VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_by VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP
);

-- AI聊天记录表
CREATE TABLE IF NOT EXISTS ai_chat_record (
    record_id INTEGER PRIMARY KEY AUTOINCREMENT,
    platform_code VARCHAR(50) NOT NULL COMMENT '平台编码',
    model VARCHAR(100) COMMENT '使用的模型',
    user_id BIGINT COMMENT '用户ID',
    prompt TEXT NOT NULL COMMENT '用户提问',
    response TEXT COMMENT 'AI回复',
    prompt_tokens INTEGER COMMENT '提问token数',
    response_tokens INTEGER COMMENT '回复token数',
    total_tokens INTEGER COMMENT '总token数',
    exec_time INTEGER COMMENT '执行时间(毫秒)',
    error_msg VARCHAR(500) COMMENT '错误信息',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 插入默认平台配置
INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
VALUES ('通义千问', 'qwen', 'your-qwen-api-key', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', 'qwen-turbo', '1', '请配置真实的API密钥');

INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
VALUES ('字节跳动-豆包', 'doubao', 'your-doubao-api-key', 'https://ark.cn-beijing.volces.com/api/v3/chat/completions', 'doubao-pro-4k', '1', '请配置真实的API密钥');

INSERT OR IGNORE INTO ai_platform_config (platform_name, platform_code, api_key, api_url, model, status, remark) 
VALUES ('百度-元宝', 'yuanbao', 'your-yuanbao-api-key', 'https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-4.0-turbo-8k', 'ernie-4.0-turbo', '1', '请配置真实的API密钥');
