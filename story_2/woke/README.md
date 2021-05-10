# Woke Authentication API

Service that manages the authentication of users.
This application uses **use case** clean architecture, due to its simplicity to the propose of this project and because
it is very clearly to release news features using this architecture. The use case uses **SOLID** concepts, mainly the **Segregation** concept where each feature is isolated of each others.
To the web challenge, this applications uses Thymeleaf framework.
To the test stack, there are 2 stacks: unit test and integration test. They are splitted in diferent packages to manage better in a possible
ci environment.
To logging, the application uses logback.
To database, the application uses flyway, starting in the init.
The application keeps saved in database the encripted password using MD5. There are already 2 examples users in database.

## Table of content
- [Stack](#stack)
- [Prerequisites](#prerequisites)
- [Environment](#environment)
- [Building and running](#building-and-running)
- [Contributing Guide](CONTRIBUTING.md)

## Stack

- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

## Prerequisites

- **[Required]** [Docker](https://www.docker.com/): As this project is dockerized.
- **[Required]** [Docker-Compose](https://docs.docker.com/compose/): To run project with its dependencies
- **[Required]** [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- **[Required]** [Kotlin](https://kotlinlang.org/)
- **[Required]** [Gradle](https://gradle.org/)
- [Optional] [PostgreSQL](https://www.postgresql.org/): To run project locally without docker

## Environment
- **Localhost** http://localhost:8080
- **Login page** http://localhost:8080/login

## Building and running

* URL API: http://localhost:8080

Some interest *targets* of gradle:

* `./gradlew detekt`: Fix simple code issues and analysis the indentation of code
* `./gradlew build`: Build all the project
* `./gradlew test`: Run unit tests
* `./gradlew componentTest`: Run integration tests

### Starting the application

Assuming you have installed:
Firstly, you have to start the postgres container in docker:
* `docker-compose up -d postgres`

After, you can user gradle to run all application task:
* `./gradlew clean detekt componentTest build`
* `./gradlew bootRun`

There are 2 users in database:
* `user1, 12345678`
* `user2, 12345678`

[Docker]: https://docs.docker.com/install/ "About Docker CE"
[Docker Compose]: https://docs.docker.com/compose/install/#install-compose "Install Docker Compose"

To verify if the application started up successfully, just try the following endpoint:

[http://localhost:8080/health](http://localhost:8000/)
