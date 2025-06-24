package com.maleka.kganya.bookapi.controllersTest;

import com.maleka.kganya.bookapi.entities.Book;
import com.maleka.kganya.bookapi.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll(); // clean the database before each test
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = Book.builder()
                .title("Test Book")
                .author("Author")
                .publishedYear(2020)
                .available(true)
                .build();

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Book")));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        bookRepository.save(Book.builder()
                .title("Sample Book")
                .author("Tester")
                .publishedYear(2022)
                .available(true)
                .build());

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author", is("Tester")));
    }

    @Test
    public void testBorrowBook() throws Exception {
        Book book = bookRepository.save(Book.builder()
                .title("Borrow Me")
                .author("Author")
                .publishedYear(2021)
                .available(true)
                .build());

        mockMvc.perform(post("/api/books/" + book.getId() + "/borrow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available", is(false)));
    }

    @Test
    public void testReturnBook() throws Exception {
        Book book = bookRepository.save(Book.builder()
                .title("Return Me")
                .author("Author")
                .publishedYear(2021)
                .available(false)
                .build());

        mockMvc.perform(post("/api/books/" + book.getId() + "/return"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available", is(true)));
    }
}

