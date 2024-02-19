# spring-boot-flyway-jooq-postgres-swagger-prettier-java-template

Create spring boot project from scratch:

https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.2&packaging=jar&jvmVersion=17&groupId=com.zlargon&artifactId=springdemo&name=springdemo&description=&packageName=com.zlargon.springdemo&dependencies=lombok,devtools,jooq,postgresql,web,actuator,flyway

## Features

| Name            | Description                     |
| --------------- | ------------------------------- |
| Spring Web MVC  |                                 |
| Spring Devtools | Hot Reload Spring boot          |
| Lombok          | Prevent boilerplate code        |
| Docker          | Run SQL Database with container |
| Postgres        | SQL Database                    |
| Flyway          | SQL Database Migration          |
| jOOQ            | Type-safe SQL ORM               |
| Swagger UI      | OpenAPI documentation           |
| Junit 5         | Unit Test                       |
| Jacoco          | Code Coverage                   |
| Prettier        | Code Formatter                  |
| Git Hooks       | Pre-commit, pre-push            |

## Prerequisite

```bash
bash

# Install by sdkman: https://sdkman.io/
sdk install java  17.0.9-graalce  # Java 17
sdk install maven 3.9.6           # Maven
sdk install jbang 0.114.0         # Jbang for running jOOQ code generator script

# Install by brew: https://brew.sh/
brew install --cask docker  # Docker Desktop
brew install yarn           # (Optional) For running scripts in package.json (yarn 1.22.x)
brew install curl           # (Optional) For running the testing curl script
brew install jq             # (Optional) For running the testing curl script
```

## Setup Postgres by docker compose

```bash
yarn up            # run docker compose up
yarn stop          # run docker compose stop
yarn down          # run docker compose down
yarn log           # show logs from docker compose
yarn docker:clean  # clean up docker volumes
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

  - spring-boot-starter-jooq (jooq 3.18.9)
  - org.flywaydb:flyway-core
  - postgresql

- document

  - springdoc-openapi-ui
    - the api document will be generated at `http://localhost:8080/swagger-ui/index.html`

- test

  - spring-boot-starter-test

- tools

  - lombok
  - commons-io
  - spring-boot-devtools
  - spring-boot-starter-actuator

## Maven Plugins

- spring-boot-maven-plugin

- exec-maven-plugin (3.1.0)

  - Install git hook

- jacoco-maven-plugin (0.8.11)

  - The code coverage report will be generated at `target/site/jacoco/index.html`

- prettier-maven-plugin (0.22)

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
