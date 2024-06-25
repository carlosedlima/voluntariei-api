# Etapa de construção
FROM ubuntu:latest AS build

# Atualiza a lista de pacotes e instala o JDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip

# Instala o Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.6-bin.zip -P /tmp && \
    unzip /tmp/gradle-7.6-bin.zip -d /opt && \
    ln -s /opt/gradle-7.6/bin/gradle /usr/bin/gradle

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para a imagem
COPY . .

# Verifica a versão do Gradle
RUN gradle --version

# Compila o projeto
RUN gradle build --no-daemon

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o JAR do build para a imagem de execução
COPY --from=build /app/build/libs/*.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]