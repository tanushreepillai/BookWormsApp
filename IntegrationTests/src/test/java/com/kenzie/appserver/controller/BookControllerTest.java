package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.controller.model.BookCreateRequest;
import com.kenzie.appserver.service.BookService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

        Books book = new Books(imageLink, description, author, title, finishedReading, bookId);

        Books persistedBook = bookService.addBook(book);

        mvc.perform(get("/books/{id}", persistedBook.getBookId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(jsonPath("imageLink")
                        .value(is(imageLink)))
                .andExpect(jsonPath("description")
                        .value(is(description)))
                .andExpect(jsonPath("author")
                        .value(is(author)))
                .andExpect(jsonPath("title")
                        .value(is(title)))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void addBook() throws Exception {

            // GIVEN
            String description = "description";
            String title = "title";
            String author = "author";
            String id = title + author.charAt(0);
            String imageLinks = "imageLinks";
            boolean finishedReading = false;

            BookCreateRequest bookCreateRequest = new BookCreateRequest();
            bookCreateRequest.setBookId(id);
            bookCreateRequest.setDescription(description);
            bookCreateRequest.setTitle(title);
            bookCreateRequest.setAuthor(author);
            bookCreateRequest.setImageLinks(imageLinks);
            bookCreateRequest.finishedReading(finishedReading);


            mapper.registerModule(new JavaTimeModule());

            // WHEN
            mvc.perform(post("/books/add")
                            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(bookCreateRequest)))
                    // THEN
                    .andExpect(jsonPath("imageLink")
                            .exists())
                    .andExpect(jsonPath("description")
                            .value(is(description)))
                    .andExpect(jsonPath("author")
                            .value(is(author)))
                    .andExpect(jsonPath("title")
                            .value(is(title)))
                    .andExpect(jsonPath("finishedReading")
                            .value(is(finishedReading)))
                    .andExpect(jsonPath("bookId")
                            .value(is(id)))

                    .andExpect(status().isCreated());
        }

    @Test
    public void getAllBooks() throws Exception {
        // GIVEN
        String imageLink = "imageLink";
        String description = "description";
        String author = "author";
        String title = "title";
        boolean finishedReading = true;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, finishedReading, bookId);
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
        boolean finishedReading = false;
        String bookId = randomUUID().toString();

        Books book = new Books(imageLink, description, author, title, finishedReading, bookId);
        bookService.addBook(book);

        boolean completed = true;

        BookCreateRequest bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setImageLinks(imageLink);
        bookCreateRequest.setDescription(description);
        bookCreateRequest.setAuthor(author);
        bookCreateRequest.setTitle(title);
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

        Books book = new Books(imageLink, description, author, title, finishedReading, bookId);
        Books persistedBook = bookService.addBook(book);

        // WHEN
        mvc.perform(delete("/books/{id}", persistedBook.getBookId())
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // THEN
                .andExpect(status().isNoContent());
    }
}