FROM maven:3.8.1-jdk-11-slim AS builder
RUN mkdir builddir
COPY . builddir
WORKDIR builddir
RUN  ["mvn","clean", "package"]

FROM maven:3.8.1-jdk-11-slim AS runner
RUN mkdir app
WORKDIR app
COPY --from=builder /builddir/target/stock-api-0.0.1-SNAPSHOT.jar stock-api.jar
CMD ["java", "-jar", "stock-api.jar","--spring.profiles.active=docker"]