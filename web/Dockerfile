FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY ../deploy/docker-compose.yml oo
COPY ../../web/target/*.jar app.jar


ENTRYPOINT ["java","-jar","/app.jar"]