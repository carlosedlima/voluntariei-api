# Etapa de construção
FROM ubuntu:latest AS build

# Atualiza a lista de pacotes e instala o JDK 17 e outras dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para a imagem
COPY . .

# Verifica a versão do Gradle Wrapper
RUN ./gradlew --version

# Baixa as dependências e compila o projeto
RUN ./gradlew build --no-daemon --stacktrace --info

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o JAR do build para a imagem de execução
COPY --from=build /app/build/libs/*.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]