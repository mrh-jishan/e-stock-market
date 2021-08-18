FROM openjdk:11
ARG JAR_FILE=target/discovery-service-*-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]