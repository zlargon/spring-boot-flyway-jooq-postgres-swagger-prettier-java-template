package com.zlargon.springdemo.dao;

import com.zlargon.springdemo.jooq.Tables;
import com.zlargon.springdemo.models.Book;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

  @Autowired
  DSLContext dsl;

  public List<Book> getBooks() {
    return dsl
      .select() //
      .from(Tables.BOOK)
      .fetchInto(Book.class);
  }

  public Book addBook(Book book) {
    return dsl
      .insertInto(Tables.BOOK) //
      .set(Tables.BOOK.TITLE, book.getTitle())
      .returning()
      .fetchOne()
      .into(Book.class);
  }
}
