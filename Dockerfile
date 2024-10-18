FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Imagem base com Debian completo
FROM debian:stable-slim

WORKDIR /app

# Instalar o Java
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get install -y gettext && \
    apt-get install -y docker-compose && \
    rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/smart-city-0.0.1-SNAPSHOT.jar app.jar
COPY docker-compose.yml /app/

RUN chmod +x /app/deploy.sh

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]