package com.graphql.graphqldemo.controller;

import com.graphql.graphqldemo.dto.BookDTO;
import com.graphql.graphqldemo.entity.Book;
import com.graphql.graphqldemo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping()
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookDTO bookRequestDTO) {
        bookService.createBookSummary(bookRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> showAllBooks() {
        return bookService.findAllBooks();
    }
}
