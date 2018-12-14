FROM        java:8
MAINTAINER  Edinei Piovesan da Silva <edinei.live@gmail.com>

EXPOSE      8080

VOLUME      /tmp
ADD         target/server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT  [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]