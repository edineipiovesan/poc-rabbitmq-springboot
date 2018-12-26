# RabbitMQ

[![Build Status](https://travis-ci.com/edineipiovesan/poc-rabbitmq-springboot.svg?branch=master)](https://travis-ci.com/edineipiovesan/poc-rabbitmq-springboot)
[![codebeat badge](https://codebeat.co/badges/2c8cf614-4c1a-4e37-a3a4-814d39039947)](https://codebeat.co/projects/github-com-edineipiovesan-poc-rabbitmq-springboot-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/296285778d074e1698087d27708a8f59)](https://app.codacy.com/app/edineipiovesan/poc-rabbitmq-springboot?utm_source=github.com&utm_medium=referral&utm_content=edineipiovesan/poc-rabbitmq-springboot&utm_campaign=Badge_Grade_Dashboard)
[![Maintainability](https://api.codeclimate.com/v1/badges/f76fa85f8b66c74346d2/maintainability)](https://codeclimate.com/github/edineipiovesan/poc-rabbitmq-springboot/maintainability)


[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=bugs)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Duplicated lines](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Lines of code](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Reliability](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Security](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Technical debt](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.github.edn.rabbitmq-springboot&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=com.github.edn.rabbitmq-springboot)

Proof of concept using RabbitMQ with Spring Boot application.

## Techcnology stack

Some of technologies used in this project

*  Java
*  Spring Boot
*  Spring AMQP
*  RabbitMQ
*  Docker
*  docker-compose
*  Maven

## Build and run

### Docker

Server and client applications runs on Docker containers with openjdk:8-jdk-alpine as base image.
RabbitMQ also can run on Docker container with official images like `rabbitmq:3-management` used here in docker-compose file.

### Build 

Builds can be done using maven Docker image. A docker-compose file in root directory can help building both application with only one command.

> From root directory, run `docker-compose -f docker-compose-build.yml up`

It will build JAR file from client and server application in their respectives target directory.

### Run

A docker-compose in root directory can launch a RabbitMQ, 4 server and 1 client containers.

> From root directory, run `docker-compose -f docker-compose.yml up --build -d`

It willl build Docker images and all containers. Containers can be listed with `docker ps` and logs can be found individually using `docker logs <container-id> --follow`.
