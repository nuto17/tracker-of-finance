FROM eclipse-temurin:17-alpine
ARG JAR_FILE=build/libs/\*.jar
COPY ${JAR_FILE} template.jar
ENTRYPOINT ["java", "-jar", "template.jar"]
