# Simple Telco App

This app will allow creation of customers, create phone numbers and activate the phone numbers.

## Prerequisite

- Java 11
- Gradle
- Docker

## Overview

In local development, you need to provide a connection to a `PostgreSQL` instance.
You can use the following docker command:
```bash
docker run -d -p 5432:5432 -e POSTGRES_DB=spring -e POSTGRES_USER=root -e POSTGRES_PASSWORD=secret postgres
```

## Running the application

Use `./gradlew bootRun` to run the application

```bash
./gradlew bootRun - Runs this project as a Spring Boot application.
```

## Running unit and Integration tests

Use `./gradlew test` to execute the unit tests

```bash
./gradlew test --tests SomeTestClass
```

Use `./gradlew integrationTest` to execute the Integration tests

```bash
./gradlew integrationTest - Run the integration tests.
```

## API Docs reference

Please refer swagger API Docs **http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config** for the API contracts
