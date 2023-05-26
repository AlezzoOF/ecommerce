FROM openjdk:17-jdk-alpine
COPY target/apk.backend.tienda.tatuajes-0.0.1-SNAPSHOT.jar java-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "java-app.jar"]

# # Utiliza una imagen base de Maven para compilar tu aplicación
# FROM openjdk:17-jdk-alpine
# VOLUME /tmp
# # Expone el puerto 8080 para que puedas acceder a tu aplicación Spring
# EXPOSE 8080
#
# # Copia el archivo pom.xml y descarga las dependencias
# COPY pom.xml .
# RUN mvn dependency:go-offline
#
# # Copia el resto del proyecto y compila la aplicación
# COPY src ./src
# RUN mvn package -DskipTests
#
# # Utiliza una imagen base de OpenJDK para ejecutar tu aplicación compilada
# FROM openjdk:11.0.12-jdk-slim
#
# # Copia el archivo JAR generado en la etapa anterior
# COPY --from=build target/your-project.jar app.jar
#
#
# EXPOSE 8080
#
# # Comando para ejecutar tu aplicación cuando se inicie el contenedor
# CMD ["java", "-jar", "app.jar"]