# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/ProjetoSmartCities-0.0.1-SNAPSHOT.jar app.jar

# Set environment variable for Spring profile (optional)
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the port your application will listen on
EXPOSE 8080

# Define the command to run your application (Adjust if needed)
CMD ["java", "-jar", "app.jar"]