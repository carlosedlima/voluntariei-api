# Etapa de construção
FROM ubuntu:latest AS build

# Atualiza a lista de pacotes e instala o JDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Instala o Gradle
RUN apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.6-bin.zip -P /tmp && \
    unzip /tmp/gradle-7.6-bin.zip -d /opt && \
    ln -s /opt/gradle-7.6/bin/gradle /usr/bin/gradle

# Copia os arquivos do projeto para a imagem
COPY . .

# Compila o projeto
RUN gradle build

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o JAR do build para a imagem de execução
COPY --from=build /build/libs/deploy_render-1.0.0.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]