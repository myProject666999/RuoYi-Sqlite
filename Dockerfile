# 若依管理系统 Dockerfile
# 使用本地构建好的 jar 包

FROM eclipse-temurin:8-jdk-alpine

# 维护者信息
LABEL maintainer="ruoyi"
LABEL description="若依管理系统-AI聚合版"

# 安装必要的工具、字体库和 AWT 支持
RUN apk add --no-cache curl tzdata sqlite fontconfig ttf-dejavu \
    libx11 libxext libxrender libxtst libxi libgcc maven

# 设置时区为上海
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建工作目录
WORKDIR /app

# 复制源代码
COPY pom.xml /app/
COPY ruoyi-common /app/ruoyi-common
COPY ruoyi-system /app/ruoyi-system
COPY ruoyi-framework /app/ruoyi-framework
COPY ruoyi-quartz /app/ruoyi-quartz
COPY ruoyi-generator /app/ruoyi-generator
COPY ruoyi-ai /app/ruoyi-ai
COPY ruoyi-admin /app/ruoyi-admin
COPY sql /app/sql

# 复制配置文件
COPY ruoyi-admin/src/main/resources/application-docker.yml /app/application-docker.yml

# 创建数据目录和上传文件目录
RUN mkdir -p /app/data /app/uploadPath

# 初始化 SQLite 数据库
RUN sqlite3 /app/data/ruoyi.db < /app/sql/sqlite.sql

# 在容器内编译项目
RUN mvn clean package -DskipTests

# 暴露端口
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=120s --retries=5 \
    CMD curl -f http://localhost:80/ || exit 1

# 设置配置文件环境变量
ENV SPRING_CONFIG_LOCATION=classpath:/,file:/app/application-docker.yml
ENV SPRING_PROFILES_ACTIVE=docker

# 启动命令
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/ruoyi-admin/target/ruoyi-admin.jar", "--spring.config.location=classpath:/,file:/app/application-docker.yml", "--spring.profiles.active=docker"]
