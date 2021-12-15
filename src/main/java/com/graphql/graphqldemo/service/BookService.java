package com.graphql.graphqldemo.service;

import com.graphql.graphqldemo.dto.BookDTO;
import com.graphql.graphqldemo.entity.Book;
import com.graphql.graphqldemo.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBookSummary(BookDTO bookDTO){
//        Long isn = System.currentTimeMillis();
        Book newBook = Book.builder().isn(bookDTO.getIsn()).title(bookDTO.getTitle()).summary(bookDTO.getSummary()).build();
        bookRepository.save(newBook);
    }

    public List<Book> findBookByTitle(String title){
        System.out.println("service:"+bookRepository.findAll());
        return bookRepository.findBookByTitleRegex(title);
    }

    public List<Book> findAllBooks(){
        System.out.println("service:"+bookRepository.findAll());
        return bookRepository.findAll();
    }

}
