# Build stage
FROM gradle:7.6-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

# Run stage
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
EXPOSE 8080
COPY --from=builder /home/gradle/project/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]