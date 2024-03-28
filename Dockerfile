FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/server-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "server-0.0.1-SNAPSHOT.jar", ">>", "/proc/1/fd/1", "2>>", "/proc/1/fd/2"]

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1
