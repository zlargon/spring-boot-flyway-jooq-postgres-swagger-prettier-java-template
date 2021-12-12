package com.zlargon.springdemo.apis;

import com.zlargon.springdemo.models.Book;
import com.zlargon.springdemo.services.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/books", produces = "application/json")
public class BooksController {

  @Autowired
  BookService bookService;

  // GET /api/v1/books
  @GetMapping
  public List<Book> getBooks() {
    return bookService.getBooks();
  }

  // POST /api/v1/books
  @PostMapping
  @ResponseBody
  public Book addBook(@RequestBody Book book) {
    return bookService.addBook(book);
  }
}
