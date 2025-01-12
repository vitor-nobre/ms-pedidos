# Etapa de build com Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa final com OpenJDK
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/producer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
