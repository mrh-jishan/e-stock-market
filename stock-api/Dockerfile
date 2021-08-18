FROM maven:3.8.1-jdk-11 as build

ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

ADD pom.xml $HOME
RUN ["mvn", "verify", "clean"]

ADD . $HOME
RUN ["mvn", "package"]


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/stock-api-*.jar
WORKDIR /opt/app
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]
