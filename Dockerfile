FROM openjdk:17

MAINTAINER Andrew Soares

COPY build/libs/people-service-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /var/cache/nginx

RUN groupadd nginxgroup

RUN adduser nginx -G nginxgroup

RUN chown nginx:nginxgroup /var/cache/nginx

USER nginx

EXPOSE	8000

ENTRYPOINT ["java", "-jar", "app.jar"]