# =================================================================
#  统一的、多阶段构建 Dockerfile (适用于父子工程)
#  请将此文件放置在您的父项目 (cloudcart-parent) 的根目录下
# =================================================================

# --- 第一阶段: 构建阶段 (Build Stage) ---
# 使用一个包含 Maven 和 JDK 17 的镜像，与您的开发环境保持一致
FROM maven:3.9.6-eclipse-temurin-17-focal AS build

# 定义一个构建参数，用于在构建时接收微服务名称
ARG SERVICE_NAME

# 设置工作目录
WORKDIR /app

# 复制所有 pom.xml 文件，以利用 Docker 的层缓存机制
# 只要 pom 文件不变，依赖就不会重新下载
COPY pom.xml .
COPY model/pom.xml ./model/
COPY user-service/pom.xml ./user-service/
COPY product-service/pom.xml ./product-service/
COPY order-service/pom.xml ./order-service/

# 下载所有依赖项
RUN mvn dependency:go-offline

# 复制所有模块的源代码
COPY model/src ./model/src
COPY user-service/src ./user-service/src
COPY product-service/src ./product-service/src
COPY order-service/src ./order-service/src

# --- 新增：执行打包命令 ---
# 这是构建可运行 jar 文件的关键步骤。
# -pl ${SERVICE_NAME} : 告诉 Maven 只构建我们通过参数指定的那个模块。
# -am : 同时构建该模块所依赖的其他模块 (例如 user-service 依赖 model)。
RUN mvn -pl ${SERVICE_NAME} -am clean package -DskipTests


# --- 第二阶段: 运行阶段 (Runtime Stage) ---
# 使用一个非常轻量的、只包含 JRE 17 的镜像作为最终镜像
FROM eclipse-temurin:17-jre-focal

# 1. 再次定义构建参数，以便在 COPY 命令中使用
ARG SERVICE_NAME
ARG SERVICE_VERSION=0.0.1-SNAPSHOT

# 2. 定义一个环境变量来设置 Spring Profile
# 我们设置一个默认值 "default"，如果在启动容器时不指定，就会使用这个值
ENV SPRING_PROFILES_ACTIVE="gcp"

# 设置工作目录
WORKDIR /app

# 3. 从构建阶段复制最终生成的 .jar 文件
COPY --from=build /app/${SERVICE_NAME}/target/${SERVICE_NAME}-${SERVICE_VERSION}.jar app.jar

# 声明容器将要监听的端口
EXPOSE 8080

# 定义容器启动时要执行的命令
# Spring Boot 会自动读取 SPRING_PROFILES_ACTIVE 这个环境变量
ENTRYPOINT ["java", "-jar", "app.jar"]
