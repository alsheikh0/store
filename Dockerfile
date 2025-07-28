# syntax=docker/dockerfile:1

# --- Build stage ---
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper and pom.xml first for dependency caching
COPY --link pom.xml mvnw ./
COPY --link .mvn .mvn/
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY --link src ./src/
COPY --link pom.xml mvnw ./
COPY --link .mvn .mvn/

# Build the application (skip tests for faster build)
RUN ./mvnw package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create a non-root user for security
RUN useradd -m appuser
USER appuser

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port the app runs on (from application.yaml: 8082)
EXPOSE 8082

# JVM options: container-aware memory settings
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=80.0", "-jar", "/app/app.jar"]
