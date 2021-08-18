FROM openjdk:11
ARG JAR_FILE=target/market-api-*-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]