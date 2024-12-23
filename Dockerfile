FROM maven:3.8.5-openjdk-22 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src
RUN mvn clean test

FROM openjdk:22-jdk-slim
WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
