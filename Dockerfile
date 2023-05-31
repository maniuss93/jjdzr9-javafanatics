# Use a base image with JDK
FROM openjdk:11-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your Spring application to the container
COPY target/OcrExerciseGenerator-web-1.0.jar app.jar

# Expose the port that the Spring application will be listening on
EXPOSE 8080

# Start the Spring application when the container starts
CMD ["java", "-jar", "app.jar"]
