FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} category.jar
ENTRYPOINT ["java","-jar","/category.jar"]