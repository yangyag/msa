FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java","-jar","/api.jar"]