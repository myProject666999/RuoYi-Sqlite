# 若依管理系统 Dockerfile (Windows容器版本)
# 使用本地构建好的 jar 包
FROM eclipse-temurin:8-jdk-windowsservercore-ltsc2022

# 维护者信息
LABEL maintainer="ruoyi"
LABEL description="若依管理系统"

# 设置时区
ENV TZ=Asia/Shanghai

# 创建工作目录
WORKDIR C:\\app

# 复制 jar 包
COPY ruoyi-admin/target/*.jar app.jar

# 复制配置文件
COPY ruoyi-admin/src/main/resources/application-docker.yml C:\\app\\application-docker.yml

# 复制 SQL 初始化脚本
COPY sql/sqlite.sql C:\\app\\sql\\sqlite.sql

# 复制已初始化的数据库文件（避免在容器中安装sqlite）
COPY data/ruoyi.db C:\\app\\data\\ruoyi.db

# 创建上传文件目录
RUN mkdir C:\\app\\uploadPath

# 暴露端口
EXPOSE 80

# 设置配置文件环境变量
ENV SPRING_CONFIG_LOCATION=classpath:/,file:/C:/app/application-docker.yml
ENV SPRING_PROFILES_ACTIVE=docker

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/,file:/C:/app/application-docker.yml", "--spring.profiles.active=docker"]
