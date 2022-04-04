package com.zlargon.springdemo;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

// https://www.baeldung.com/spring-dynamicpropertysource#an-alternative-test-fixtures
public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

  private PostgreSQLContainer<?> container;

  private final String DB_DATABASE = "postgres";
  private final String DB_USERNAME = "postgres";
  private final String DB_PASSWORD = "postgres";
  private final String DB_SCHEMA = "spring_demo";

  @Override
  public void beforeAll(ExtensionContext context) throws InterruptedException {
    // create postgres container
    container =
      new PostgreSQLContainer<>("postgres:14")
        .withUrlParam("currentSchema", DB_SCHEMA)
        .withDatabaseName(DB_DATABASE)
        .withUsername(DB_USERNAME)
        .withPassword(DB_PASSWORD);

    // start postgres container
    container.start();

    // get postgres container port number and JDBC URL
    final Integer DB_PORT = container.getFirstMappedPort();
    final String JDBC_URL = String.format(
      "jdbc:postgresql://localhost:%d/%s?currentSchema=%s",
      DB_PORT,
      DB_DATABASE,
      DB_SCHEMA
    );
    System.out.println("JDBC_URL = " + JDBC_URL);
    System.out.println("DB_PORT = " + DB_PORT);

    // override the application properties
    System.setProperty("spring.datasource.url", JDBC_URL);
    System.setProperty("spring.datasource.username", DB_USERNAME);
    System.setProperty("spring.datasource.password", DB_PASSWORD);

    // wait for postgres container up before doing flyway migration
    Thread.sleep(8000);

    // flyway data migration
    Flyway.configure().dataSource(JDBC_URL, DB_USERNAME, DB_PASSWORD).load().migrate();
  }

  @Override
  public void afterAll(ExtensionContext context) {
    // do nothing, Testcontainers handles container shutdown
  }
}
