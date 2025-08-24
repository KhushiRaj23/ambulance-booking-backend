# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (for caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw.cmd ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the Spring Boot application (skip tests for faster builds)
RUN ./mvnw clean package -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre AS runtime

# Set working directory
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Create non-root user
RUN useradd -m appuser
USER appuser

# Expose application port
EXPOSE 8080

# Healthcheck (optional)
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
