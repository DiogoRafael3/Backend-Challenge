FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

WORKDIR /opt/workdir/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]