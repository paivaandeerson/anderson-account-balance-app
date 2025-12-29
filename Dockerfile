FROM amazoncorretto:21-al2023-headless

WORKDIR /app

# Copiar o JAR
# O asterisco (*) garante que ele pegue o arquivo mesmo que a versão mude
COPY target/*.jar app.jar

EXPOSE 8081

# Comando para iniciar a aplicação
# Adicionamos parâmetros de otimização para containers
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "app.jar"]
