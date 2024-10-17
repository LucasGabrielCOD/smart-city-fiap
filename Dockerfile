# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

# Criar usuário e grupo "appuser"
RUN addgroup -S appuser && adduser -S appuser -G appuser

# Mudar para o usuário "appuser"
USER appuser

COPY --from=build /app/target/smart-city-0.0.1-SNAPSHOT.jar app.jar

RUN apt-get update && apt-get install -y gettext

COPY deploy.sh /app/

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]