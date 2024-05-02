FROM amazoncorretto:18-alpine-jdk

# Creamos el directorio donde se alojará la aplicación
RUN mkdir /app

# Copiamos el jar y los JSON
COPY target/*.jar /app/app.jar


# Definimos el directorio de trabajo
WORKDIR /app

# Exponemos el puerto 8080
EXPOSE 8080

# Ejecutamos la aplicación
CMD ["java", "-jar", "app.jar"]
