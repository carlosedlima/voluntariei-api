# Etapa de construção
FROM gradle:7.4.2-jdk11 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# Etapa final
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/voluntariei-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
