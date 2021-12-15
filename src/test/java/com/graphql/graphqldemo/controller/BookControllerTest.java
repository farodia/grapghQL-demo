package com.graphql.graphqldemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.graphql.graphqldemo.dto.BookDTO;
import com.graphql.graphqldemo.entity.Book;
import com.graphql.graphqldemo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void add_book() throws Exception {
        BookDTO mockBook = BookDTO.builder().isn("1").title("mock title1").summary("mock summary1").build();
        doNothing().when(bookService).createBookSummary(mockBook);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(mockBook);
        String url="http://localhost:8091";
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());

    }

    @Test
    public void show_books() throws Exception {
        List<Book> books = Arrays.asList(
                Book.builder().isn("1").title("mock title1").summary("mock summary1").build(),
                Book.builder().isn("2").title("mock title2").summary("mock summary2").build()
        );
        Mockito.when(bookService.findAllBooks())
                .thenReturn(books);
        String url="http://localhost:8091";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
