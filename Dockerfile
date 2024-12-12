# Use an official OpenJDK runtime as the base image
FROM eclipse-temurin:21-jdk-alpine

ENV SPRING_PROFILES_ACTIVE=local

# Set the working directory
WORKDIR /app

# Copy the built Spring Boot jar into the container
COPY target/bills-rest-server-0.0.1-SNAPSHOT.jar app.jar

# Expose the default port (Cloud Run uses port 8080 by default)
EXPOSE 8080

# Set the entry point to run the jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
