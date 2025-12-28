# 1. Usamos uma imagem leve apenas com o JRE (ambiente de execução)
FROM amazoncorretto:21-al2023-headless

# 2. Definimos a pasta de trabalho dentro do container
WORKDIR /app

# 3. Copiamos o JAR que VOCÊ gerou com o comando ./mvnw clean package
# O asterisco (*) garante que ele pegue o arquivo mesmo que a versão mude
COPY target/*.jar app.jar

# 4. Expomos a porta da API
EXPOSE 8081

# 5. Comando para iniciar a aplicação
# Adicionamos parâmetros de otimização para containers
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "app.jar"]