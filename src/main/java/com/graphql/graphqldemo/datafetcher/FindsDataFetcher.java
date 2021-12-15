package com.graphql.graphqldemo.datafetcher;

import com.graphql.graphqldemo.entity.Book;
import com.graphql.graphqldemo.service.BookService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class FindsDataFetcher {
    private final BookService bookService;

    public FindsDataFetcher(BookService bookService) {
        this.bookService = bookService;
    }

    @DgsQuery
    public List<Book> finds(@InputArgument("titleFilter") String titleFilter) {
        if (titleFilter == null) {
            return bookService.findAllBooks();
        }

        System.out.println("query"+bookService.findBookByTitle(titleFilter));
        return bookService.findBookByTitle(titleFilter);
    }
}
