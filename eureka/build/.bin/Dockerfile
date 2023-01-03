# Docker image for springboot application
# VERSION 0.0.1
# Author: bolingcavalry

### 基础镜像，使用alpine操作系统，openjkd使用8u201
FROM maven:3-jdk-8-alpine-add-font

#作者
MAINTAINER xtpeach

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

#声明�?个挂载点，容器内此路径会对应宿主机的某个文件�?
VOLUME /tmp

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD app/eureka-final-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=${JAVA_OPTS} \
    JAR_OPTS=${JAR_OPTS}
#启动容器时的进程
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar $JAR_OPTS" ]

ENV PORT 18080
