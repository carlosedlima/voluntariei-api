# Usando uma imagem base do JDK 11
FROM openjdk:11-jdk-slim

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o arquivo JAR gerado pelo Gradle para dentro do container
COPY build/libs/*.jar app.jar

# Expondo a porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]