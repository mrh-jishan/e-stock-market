FROM maven:3.8.1-jdk-11 as build

ENV HOME=/home/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME
RUN ["mvn", "verify", "clean"]

ADD . $HOME
RUN ["mvn", "package"]


FROM maven:3.8.1-jdk-11
ARG JAR_FILE=target/stock-api-*.jar
# cd /opt/app
WORKDIR /opt/app
# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=docker"]

#VOLUME /tmp
#ARG DEPENDENCY=/home/usr/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","-Dspring.profiles.active=docker","com/stock/stockapi/StockApiApplication"]
