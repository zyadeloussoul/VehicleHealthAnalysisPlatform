FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml ./pom.xml
COPY telemetry-service/pom.xml ./telemetry-service/pom.xml
COPY vehicle-analysis-service/pom.xml ./vehicle-analysis-service/pom.xml

COPY telemetry-service/src ./telemetry-service/src

RUN mvn -pl telemetry-service -am clean package -DskipTests



FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build \
    /workspace/telemetry-service/target/*.jar \
    app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]