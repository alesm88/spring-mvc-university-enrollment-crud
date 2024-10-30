# Image Base of Java 21
# FROM openjdk:21-jdk-slim
FROM openjdk:21

# Create a directory fir the application
WORKDIR /app

# Copy the JAR file generate by Maven in the container
# COPY target/spring-mvc-university-enrollment-crud-0.0.1-SNAPSHOT.jar app.jar
COPY target/spring-boot-challenge-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that will run the application
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]