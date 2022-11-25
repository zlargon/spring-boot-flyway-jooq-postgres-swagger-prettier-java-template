# Spring Boot Demo

Create spring boot project from scratch:

https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.7.6&packaging=jar&jvmVersion=11&groupId=com.zlargon&artifactId=springdemo&name=springdemo&description=&packageName=com.zlargon.springdemo&dependencies=lombok,devtools,jooq,postgresql,web,actuator,testcontainers,flyway

## Prerequisite

- bash
- Docker Desktop: `brew install --cask docker`
- Maven: `sdk install maven 3.8.4`
- Java 11: `sdk install java 11.0.13-zulu`
- yarn: `brew install yarn`
- curl (Optional): `brew install curl`
- jq (Optional): `brew install jq`

## Docker

```bash
yarn up      # run docker compose up
yarn stop    # run docker compose stop
yarn down    # run docker compose down
yarn log     # show logs from docker compose
```

## Commands

```bash
yarn start         # run spring boot application
yarn test          # run test by console launcher
yarn coverage      # run test with code coverage
yarn clean         # clean the project
yarn format        # format the source code
yarn jooq:codegen  # run script to update jooq generated code
yarn doc           # open swagger-ui for API documentation
```

# Test

1. Run postgres at port 5700 by docker

   ```bash
   yarn up
   yarn log  # check the logs from docker
   ```

2. Run Spring boot service

   ```bash
   yarn start
   ```

3. Run curl command to check the apis

   ```bash
   ./curl/create_book.sh # POST /api/v1/books
   ./curl/get_book.sh    # GET  /api/v1/books
   ```

## Dependencies

- api

  - spring-boot-starter-web

- database

  - spring-boot-starter-jooq
  - org.flywaydb:flyway-core
  - postgresql

- document

  - springdoc-openapi-ui
    - the api document will be generated at `http://localhost:8080/swagger-ui`

- test

  - spring-boot-starter-test
  - junit-platform-console-standalone
  - org.testcontainers:testcontainers-bom
  - org.testcontainers:junit-jupiter
  - org.testcontainers:postgresql

- tools

  - lombok
  - spring-boot-devtools
  - spring-boot-starter-actuator

## Plugins

- spring-boot-maven-plugin

- exec-maven-plugin

  - Install git hook

- jacoco-maven-plugin

  - The code coverage report will be generated at `target/site/jacoco/index.html`

- prettier-maven-plugin

  - format the source code
    - printWidth: 120
    - tabWidth: 2
    - useTabs: false
