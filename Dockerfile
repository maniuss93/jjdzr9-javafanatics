# Use a base image with JDK
FROM maven:3.9.2-eclipse-temurin-17 AS Builder

# Set the working directory inside the container
WORKDIR /app

ADD OcrExerciseGenerator-web/pom.xml web/pom.xml
ADD OcrExerciseGenerator-web/src web/src/

ADD OcrExerciseGenerator-core/pom.xml core/pom.xml
ADD OcrExerciseGenerator-core/src core/src/

RUN mvn clean install -f ./core/pom.xml
RUN mvn clean install -f ./web/pom.xml

FROM eclipse-temurin:17-jre-alpine

COPY --from=Builder /app/web/target/OcrExerciseGenerator-web-1.0.jar app.jar

# Expose the port that the Spring application will be listening on
EXPOSE 8080

# Start the Spring application when the container starts
CMD ["java", "-jar", "app.jar"]
