package com.graphql.graphqldemo.dgsgraphqltest;

import com.graphql.graphqldemo.datafetcher.FindsDataFetcher;
import com.graphql.graphqldemo.entity.Book;
import com.graphql.graphqldemo.service.BookService;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import graphql.ExecutionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = {DgsAutoConfiguration.class, FindsDataFetcher.class})
public class FindsDataFetcherTest {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    BookService bookService;

    String title = "on Java";

    @BeforeEach
    public void before() {
        Mockito.when(bookService.findAllBooks())
                .thenAnswer(invocation -> Collections.singletonList(Book.builder().isn("1").title(title).summary("mock summary").build()));
        Mockito.when(bookService.findBookByTitle(title))
                .thenAnswer(invocation -> Collections.singletonList(Book.builder().isn("1").title(title).summary("mock summary").build()));

    }

    @Test
    void finds_without_input_arguments() {
        List<Book> books = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ finds { isn title summary }}",
                "data.finds[*]"
        );
        System.out.println(books.get(0));
        assertThat(books.size()).isEqualTo(1);
//        assertThat(books.get(0).getTitle()).contains(title);
    }

    @Test
    void finds_with_input_arguments() {
        List<Book> books = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ finds(titleFilter: \"on Java\") { isn title summary }}",
                "data.finds[*]"
        );
        System.out.println(books.get(0));
        assertThat(books.size()).isEqualTo(1);
//        assertThat(books.get(0).getTitle()).contains(title);
    }


    @Test
    void finds_with_exception_without_input_arguments() {
        Mockito.when(bookService.findAllBooks()).thenThrow(new RuntimeException("nothing to see here"));

        ExecutionResult result = dgsQueryExecutor.execute(
                " { finds { isn title summary }}");

        assertThat(result.getErrors()).isNotEmpty();
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("java.lang.RuntimeException: nothing to see here");
    }

    @Test
    void finds_with_exception_with_input_arguments() {
        Mockito.when(bookService.findBookByTitle(title)).thenThrow(new RuntimeException("nothing to see here"));

        ExecutionResult result = dgsQueryExecutor.execute(
                " {\n" +
                        "  finds(titleFilter: \"on Java\") {\n" +
                        "    isn\n" +
                        "    title\n" +
                        "    summary\n" +
                        "  } \n" +
                        "  \n" +
                        "}");

        assertThat(result.getErrors()).isNotEmpty();
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("java.lang.RuntimeException: nothing to see here");
    }


}
