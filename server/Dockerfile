FROM    openjdk:8-jdk-alpine
LABEL   org.label-schema.maintainer.name="Edinei Piovesan da Silva" \
        org.label-schema.maintainer.email="edinei.live@gmail.com" \
        org.label-schema.name="RabbitMQ + Spring Boot" \
        org.label-schema.description="Proof of concept for RabbitMQ with Spring Boot application" \
        org.label-schema.schema-version="1.0"

VOLUME  /tmp
EXPOSE  8080

ARG     JAR_FILE=target/server-0.0.1-SNAPSHOT.jar
COPY    $JAR_FILE /app.jar

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/app.jar"]