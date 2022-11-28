package com.zlargon.springdemo.dao;

import com.zlargon.springdemo.jooq.Tables;
import com.zlargon.springdemo.models.Book;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

  private final DSLContext jooq;

  public BookDao(DSLContext jooq) {
    this.jooq = jooq;
  }

  public List<Book> getBooks() {
    return jooq
      .select() //
      .from(Tables.BOOK)
      .fetchInto(Book.class);
  }

  public Book addBook(Book book) {
    return jooq
      .insertInto(Tables.BOOK) //
      .set(Tables.BOOK.TITLE, book.getTitle())
      .returning()
      .fetchOne()
      .into(Book.class);
  }
}
