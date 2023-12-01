FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/server-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]
