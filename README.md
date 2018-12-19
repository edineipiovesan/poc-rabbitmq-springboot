# RabbitMQ
Proof of concept using RabbitMQ with Spring Boot application.

## Techcnology stack
- Java
- Spring Boot
- Spring AMQP
- RabbitMQ
- Docker
- docker-compose
- Maven

# Build and run
## Docker
Server and client applications runs on Docker containers with openjdk:8-jdk-alpine as base image.
RabbitMQ also can run on Docker container with official images like ```rabbitmq:3-management``` used here in docker-compose file.

## Build 
Builds can be done using maven Docker image. A docker-compose file in root directory can help building both application with only one command.
- From root directory, run ```docker-compose -f docker-compose-build.yml up```

It will build JAR file from client and server application in their respectives target directory.

## Run
A docker-compose in root directory can launch a RabbitMQ, 4 server and 1 client containers.
- From root directory, run ```docker-compose -f docker-compose.yml up --build -d```

It willl build Docker images and all containers. Containers can be listed with ```docker ps``` and logs can be found individually using ```docker logs <container-id> --follow```.

# TODO

- Write tests
- Log service
- Setup kubernetes
- Write C# .Net CORE consumer