# Use a lightweight Java image
FROM openjdk:21-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/assessment-0.0.1-SNAPSHOT.jar /app/assessment.jar

# Expose the port your app listens on
EXPOSE 8080

# Command to run your JAR file
CMD ["java", "-jar", "assessment.jar"]
