# spring-boot-flyway-jooq-postgres-swagger-prettier-java-template

Create spring boot project from scratch:

https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.7.6&packaging=jar&jvmVersion=11&groupId=com.zlargon&artifactId=springdemo&name=springdemo&description=&packageName=com.zlargon.springdemo&dependencies=lombok,devtools,jooq,postgresql,web,actuator,testcontainers,flyway

## Features

| Name            | Description                      |
| --------------- | -------------------------------- |
| Spring Web MVC  |                                  |
| Spring Devtools | Hot Reload Spring boot           |
| Lombok          | Prevent boilerplate code         |
| Docker          | Run SQL Database with container  |
| Postgres        | SQL Database                     |
| Flyway          | SQL Database Migration           |
| jOOQ            | Typesafe SQL ORM                 |
| Swagger UI      | OpenAPI documentation            |
| Junit 5         | Unit Test                        |
| Jacoco          | Code Coverage                    |
| Test Container  | Run docker container for testing |
| Prettier        | Code Formatter                   |
| Git Hooks       | Pre-commit, pre-push             |

## Prerequisite

```bash
bash

# Install by sdkman: https://sdkman.io/
sdk install java 11.0.17-zulu  # Java 11
sdk install maven 3.8.6        # Maven
sdk install jbang 0.101.0      # Jbang for running jOOQ code generator script

# Install by brew: https://brew.sh/
brew install --cask docker  # Docker Desktop
brew install yarn           # (Optional) For running scripts in package.json (yarn 1.22.x)
brew install curl           # (Optional) For running the testing curl script
brew install jq             # (Optional) For running the testing curl script
```

## Setup Postgres by docker compose

```bash
yarn up      # run docker compose up
yarn stop    # run docker compose stop
yarn down    # run docker compose down
yarn log     # show logs from docker compose
```

## Scripts

```bash
yarn start         # run spring boot application
yarn test          # run tests
yarn coverage      # run tests with code coverage and open the report website
yarn clean         # clean the project
yarn format        # format the source code
yarn jooq:codegen  # run script to update jooq generated code
yarn doc           # open swagger-ui website for API documentation
```

## Run Spring Application

1. Run postgres at port 5700 by docker

   ```bash
   yarn up
   yarn log  # check the logs from docker compose
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
  - org.testcontainers:testcontainers-bom
  - org.testcontainers:junit-jupiter
  - org.testcontainers:postgresql

- tools

  - lombok
  - spring-boot-devtools
  - spring-boot-starter-actuator

## Maven Plugins

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

## VSCode Plugins

- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack)
- [SonarLint](https://marketplace.visualstudio.com/items?itemName=SonarSource.sonarlint-vscode)
- [Prettier - Code formatter](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)
- [Code Spell Checker](https://marketplace.visualstudio.com/items?itemName=streetsidesoftware.code-spell-checker)
- [ShellCheck](https://marketplace.visualstudio.com/items?itemName=timonwong.shellcheck)
