FROM maven:3.8.1-jdk-11 as build

ENV HOME=/home/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

ADD . $HOME
RUN ["mvn", "package"]

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM maven:3.8.1-jdk-11
VOLUME /tmp
ARG DEPENDENCY=/home/usr/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","AuthApiApplication"]
