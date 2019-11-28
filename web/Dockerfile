FROM openjdk:8-jdk-alpine

VOLUME /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar", "--server.port=8080"]