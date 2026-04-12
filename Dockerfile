# 若依管理系统 Dockerfile
# 使用本地构建好的 jar 包

FROM eclipse-temurin:8-jre-alpine

# 维护者信息
LABEL maintainer="ruoyi"
LABEL description="若依管理系统"

# 安装必要的工具、字体库和 AWT 支持
RUN apk add --no-cache curl tzdata sqlite fontconfig ttf-dejavu \
    libx11 libxext libxrender libxtst libxi libgcc

# 设置时区为上海
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建工作目录
WORKDIR /app

# 复制 jar 包
COPY ruoyi-admin/target/*.jar app.jar

# 复制配置文件
COPY ruoyi-admin/src/main/resources/application-docker.yml /app/application-docker.yml

# 复制 SQL 初始化脚本
COPY sql/sqlite.sql /app/sql/sqlite.sql

# 创建数据目录和上传文件目录
RUN mkdir -p /app/data /app/uploadPath

# 初始化 SQLite 数据库
RUN sqlite3 /app/data/ruoyi.db < /app/sql/sqlite.sql

# 暴露端口
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:80/ || exit 1

# 设置配置文件环境变量
ENV SPRING_CONFIG_LOCATION=classpath:/,file:/app/application-docker.yml
ENV SPRING_PROFILES_ACTIVE=docker

# 启动命令
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.config.location=classpath:/,file:/app/application-docker.yml", "--spring.profiles.active=docker"]
