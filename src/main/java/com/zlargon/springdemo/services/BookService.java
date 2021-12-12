package com.zlargon.springdemo.services;

import com.zlargon.springdemo.dao.BookDao;
import com.zlargon.springdemo.models.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  BookDao bookDao;

  /**
   * 1. get books
   */
  public List<Book> getBooks() {
    return bookDao.getBooks();
  }

  /**
   * 2. add book
   */
  public Book addBook(Book book) {
    return bookDao.addBook(book);
  }
}
