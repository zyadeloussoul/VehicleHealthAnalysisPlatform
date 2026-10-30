FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml ./pom.xml
COPY telemetry-service/pom.xml ./telemetry-service/pom.xml
COPY vehicle-analysis-service/pom.xml ./vehicle-analysis-service/pom.xml

COPY vehicle-analysis-service/src ./vehicle-analysis-service/src

RUN mvn -pl vehicle-analysis-service -am clean package -DskipTests



FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build \
    /workspace/vehicle-analysis-service/target/*.jar \
    app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]