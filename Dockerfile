FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=build/libs/\*.jar
COPY ${JAR_FILE} finance.jar
ENTRYPOINT ["java", "-jar", "finance.jar"]