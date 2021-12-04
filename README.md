# Simple Telco App

This app will allow creation of customers, create phone numbers and activate the phone numbers.

## Overview

In local development, you need to provide a connection to a `PostgreSQL` instance.
You can use the following docker command:
```bash
docker run -d -p 5432:5432 -e POSTGRES_DB=spring -e POSTGRES_USER=root -e POSTGRES_PASSWORD=secret postgres
```


## Running unit tests

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

Use `./gradlew test` to execute the unit tests
Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/test/index.html) file in your web browser.

```bash
./gradlew test --tests SomeTestClass
```

Please refer swagger docs http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config for the API contracts
