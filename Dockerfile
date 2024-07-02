# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/todo-app.jar /app/todo-app.jar

# Copy the configuration file into the container
COPY config.yml /app/config.yml

# Expose the port the application will run on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "todo-app.jar", "server", "config.yml"]
