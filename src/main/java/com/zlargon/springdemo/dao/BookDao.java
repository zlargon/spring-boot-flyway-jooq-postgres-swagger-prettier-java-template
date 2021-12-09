package com.zlargon.springdemo.dao;

import java.util.List;

import com.zlargon.springdemo.jooq.Tables;
import com.zlargon.springdemo.models.Book;
import com.zlargon.springdemo.services.MapperService;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

  @Autowired
  DSLContext dsl;

  @Autowired
  MapperService mapper;

  public List<Book> getBooks() {
    return mapper.toList(Book.class, dsl
      .select()
      .from(Tables.BOOK)
      .fetch());
  }

  public Book addBook(Book book) {
    return mapper.toObject(Book.class, dsl
      .insertInto(Tables.BOOK)
      .set(Tables.BOOK.TITLE, book.getTitle())
      .returning()
      .fetchOne());
  }
}
