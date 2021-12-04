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


## Endpoints

- List all the Phone numbers
```curl
curl --location --request GET 'http://localhost:8080/phonenumber'
curl --location --request GET 'http://localhost:8080/phonenumber?pageNo=0&pageSize=2&sortyBy=id'
```

- Assign a phone number to a Customer

```curl
curl --location --request POST 'http://localhost:8080/customer/' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"name": "aaa",
"email": "ss@ss.com",
"phoneNumbers": [
{
"id": 4,
"number": "0444444444",
"status": "ACTIVE"
}
]
}'
```

- Get all the phone number allocated to a Customer

```curl
curl --location --request GET 'http://localhost:8080/customer/1' \
--data-raw ''
```

- To Activate a phone number
```curl
curl --location --request PUT 'http://localhost:8080/phonenumber/6' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 6,
"number": "0466666666",
"status": "ACTIVE"
}'
```