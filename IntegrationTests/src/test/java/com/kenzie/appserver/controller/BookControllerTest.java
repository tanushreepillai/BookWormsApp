package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.controller.model.BookCreateRequest;
import com.kenzie.appserver.service.BookService;
import com.kenzie.appserver.service.ExampleService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.util.HashSet;
import java.util.Set;

import static java.util.UUID.randomUUID;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    BookService bookService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {

        String imageLink = "imageLink";
        String description = "description";
        String author = "author";
        String title = "title";
        String infoLink = "infoLink";
        boolean finishedReading = true;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, infoLink, finishedReading, bookId);

        Books persistedBook = bookService.addBook(book);

        mvc.perform(get("/books/{id}", persistedBook.getBookId())
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // THEN
                .andExpect(jsonPath("imageLink")
                        .value(is(imageLink)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(jsonPath("infoLink")
                        .value(is(infoLink)))
                .andExpect(jsonPath("finishedReading")
                        .value(is(finishedReading)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getById_bookDoesNotExist() throws Exception {
        // GIVEN
        String bookId = randomUUID().toString();
        // WHEN
        mvc.perform(get("/books/{id}", bookId)
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // THEN
                .andExpect(status().isNotFound());
    }

//    @Test
//    public void createExample_CreateSuccessful() throws Exception {
//        String name = mockNeat.strings().valStr();
//
//        BookCreateRequest exampleCreateRequest = new BookCreateRequest();
//        exampleCreateRequest.setName(name);
//
//        mapper.registerModule(new JavaTimeModule());
//
//        mvc.perform(post("/example")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(exampleCreateRequest)))
//                .andExpect(jsonPath("id")
//                        .exists())
//                .andExpect(jsonPath("name")
//                        .value(is(name)))
//                .andExpect(status().is2xxSuccessful());
//    }

    @Test
    public void addBook() {
        // TODO: Come back after bookService.addBook is created
    }

    @Test
    public void getAllBooks() throws Exception {
        // GIVEN
        String imageLink = "imageLink";
        String description = "description";
        String author = "author";
        String title = "title";
        String infoLink = "infoLink";
        boolean finishedReading = true;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, infoLink, finishedReading, bookId);
        bookService.addBook(book);

        // WHEN
        Set<Books> books = bookService.getAllBooks();
        mvc.perform(get("/books/all", books)
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // THEN
                .andExpect(status().isOk());
        Assertions.assertNotNull(bookService.getAllBooks());
    }

    @Test
    public void updateBook_PutSuccessful() throws Exception {
        // GIVEN
        String imageLink = "imageLink";
        String description = "description";
        String author = "author";
        String title = "title";
        String infoLink = "infoLink";
        boolean finishedReading = false;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, infoLink, finishedReading, bookId);
        bookService.addBook(book);

        boolean completed = true;

        BookCreateRequest bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setImageLinks(imageLink);
        bookCreateRequest.setDescription(description);
        bookCreateRequest.setAuthors(author);
        bookCreateRequest.setTitle(title);
        bookCreateRequest.setInfoLink(infoLink);
        bookCreateRequest.finishedReading(completed);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/books")
                        .accept(String.valueOf(MediaType.APPLICATION_JSON))
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(bookCreateRequest)))
                // THEN
                .andExpect(jsonPath("finishedReading")
                        .value(is(true)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBook_DeleteSuccessful() throws Exception {
        // GIVEN
        String imageLink = "imageLink";
        String description = "description";
        String author = "author";
        String title = "title";
        String infoLink = "infoLink";
        boolean finishedReading = false;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, infoLink, finishedReading, bookId);
        Books persistedBook = bookService.addBook(book);

        // WHEN
        mvc.perform(delete("/books/{id}", persistedBook.getBookId())
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // THEN
                .andExpect(status().isNoContent());
        Assertions.assertNull(bookService.findById(bookId));
    }
}