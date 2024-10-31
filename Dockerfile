# Using a base image of Maven to COMPILE the project
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Create a directory for the application
WORKDIR /app

# Copy the file `pom.xml` y the source files to the container
COPY pom.xml .
COPY src ./src

# Run the Maven command to pack the application into a JAR
RUN mvn clean package -DskipTests
# Now the JAR file will be in `target/`
# Is possible to create another stage of Docker for the final image (if there is multi-stage builds)

# Here we use a Java image to RUN the app
FROM openjdk:21

# Define the working directory to the final image
WORKDIR /app

# Copy the JAR generated in the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that will run the application
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]