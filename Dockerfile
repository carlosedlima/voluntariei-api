FROM ubuntu:latest AS build

# Atualizar pacotes e instalar JDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip

# Instalar Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip
RUN unzip gradle-7.4.2-bin.zip -d /opt
RUN ln -s /opt/gradle-7.4.2/bin/gradle /usr/bin/gradle

# Configurar diretório de trabalho
WORKDIR /app

# Copiar todos os arquivos para o contêiner
COPY . .

# Conceder permissão de execução ao Gradle Wrapper
RUN chmod +x gradlew

# Listar arquivos para debug
RUN ls -la

# Executar o build
RUN ./gradlew bootJar --no-daemon

# Usar uma imagem base mais leve para a etapa final
FROM openjdk:17-jdk-slim

# Configurar diretório de trabalho
WORKDIR /app

# Expor a porta 8080
EXPOSE 8080

# Copiar o JAR gerado da etapa de construção
COPY --from=build /app/build/libs/voluntariei-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]