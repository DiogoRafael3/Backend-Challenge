FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR .

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine
ADD target/challenge-1.0.0.jar challenge-1.0.0.jar
ENTRYPOINT ["java", "-jar","challenge-1.0.0.jar"]
EXPOSE 8080