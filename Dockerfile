FROM openjdk:11-jdk-slim

VOLUME /tpm

ADD /target/rest-with-spring-boot-udemy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]