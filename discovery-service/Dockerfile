FROM maven:3.8.1-jdk-11 AS builder
RUN mkdir builddir
COPY . builddir
WORKDIR builddir
RUN  ["mvn","clean", "package"]

FROM maven:3.8.1-jdk-11 AS runner
RUN mkdir app
WORKDIR app
COPY --from=builder /builddir/target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar
CMD ["java", "-jar", "discovery-service.jar","--spring.profiles.active=docker"]



