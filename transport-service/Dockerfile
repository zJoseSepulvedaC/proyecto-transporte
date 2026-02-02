# Usamos Java 17
FROM eclipse-temurin:17-jdk-jammy

# Carpeta de trabajo
WORKDIR /app

# Copiamos los archivos del proyecto
COPY . .

# Damos permiso de ejecución al instalador de Maven
RUN chmod +x mvnw

# Comando para iniciar la app
CMD ["./mvnw", "spring-boot:run"]