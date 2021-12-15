package com.graphql.graphqldemo.repository;

import com.graphql.graphqldemo.entity.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface BookRepository extends MongoRepository<Book, String> {
//    List<Book> findBookByTitleLike;
    List<Book> findBookByTitleRegex(String title);

    @Override
    List<Book> findAll();
}
