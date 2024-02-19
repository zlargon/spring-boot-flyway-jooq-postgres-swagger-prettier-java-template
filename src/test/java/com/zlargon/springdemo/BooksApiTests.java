package com.zlargon.springdemo;

import static org.assertj.core.api.Assertions.*;

import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
@TestInstance(Lifecycle.PER_CLASS) // Enable @BeforeAll with non-static method
@TestMethodOrder(OrderAnnotation.class) // Enable @Order Annotation
class BooksApiTests {

  private final MockMvc mockMvc;
  private final JdbcTemplate jdbc;

  @Autowired
  public BooksApiTests(MockMvc mockMvc, JdbcTemplate jdbc) {
    this.mockMvc = mockMvc;
    this.jdbc = jdbc;
  }

  @BeforeAll
  public void beforeAll() throws Exception {
    // clean up all rules tables for a fresh test
    jdbc.execute(IOUtils.toString(new FileInputStream("src/test/resources/sql/truncate_all_tables.sql"), "UTF8"));
  }

  @Test
  @Order(1)
  @DisplayName("Expect to get empty book list first time")
  void getBook() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .request(HttpMethod.GET, "/api/v1/books") //
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk()) // 200
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
  }

  @Test
  @Order(2)
  @DisplayName("Expect to create a book, and get book list with new book")
  void createBook() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .request(HttpMethod.POST, "/api/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            """
            {"title":"my book"}"""
          )
      )
      .andExpect(MockMvcResultMatchers.status().isOk()) // 200
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("my book")));

    // expect to see new book from the list
    String jsonResponse = mockMvc
      .perform(
        MockMvcRequestBuilders
          .request(HttpMethod.GET, "/api/v1/books") //
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk()) // 200
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
      .andReturn()
      .getResponse()
      .getContentAsString();

    assertThat(jsonResponse)
      .isEqualTo(
        """
        [{"id":1,"title":"my book"}]"""
      );
  }
}
