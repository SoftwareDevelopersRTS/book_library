# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory
WORKDIR /book_library

# Copy the Maven project files to the container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /book_library/target/book_library-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]