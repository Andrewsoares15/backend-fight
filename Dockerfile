FROM openjdk:17

MAINTAINER Andrew Soares

COPY build/libs/people-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE	8000

ENTRYPOINT [ "java", "-XX:+UseParallelGC", "-XX:MaxRAMPercentage=75", "--enable-preview", "-jar", "app.jar" ]