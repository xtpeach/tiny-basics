FROM openjdk:8-jdk-alpine
MAINTAINER xtpeach
ADD app/{project.name}-{project.version}.jar app.jar
ENV JAVA_OPTS=${JAVA_OPTS} \
    JAR_OPTS=${JAR_OPTS} \
    LANG=C.UTF-8 LC_ALL=C.UTF-8
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar $JAR_OPTS" ]
EXPOSE 31000