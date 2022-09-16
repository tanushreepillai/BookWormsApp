package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BookRecord;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.BooksData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.util.*;

import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;
    private LambdaServiceClient lambdaServiceClient;

    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        lambdaServiceClient = mock(LambdaServiceClient.class);
        bookService = new BookService(bookRepository, lambdaServiceClient);
    }
    /** ------------------------------------------------------------------------
     *  exampleService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();

        BooksData booksData = new BooksData(
                "imageLink",
                "description",
                "author",
                "title",
                "infoLink",
                id,
                "category",
                true);

        // WHEN
        when(lambdaServiceClient.getBookData(id)).thenReturn(booksData);
        Books book = bookService.findById(id);

        // THEN
        Assertions.assertNotNull(book, "The book is returned");
        Assertions.assertEquals(booksData.getImageLink(), book.getImageLink(), "The imageLink matches");
        Assertions.assertEquals(booksData.getDescription(), book.getDescription(), "The description matches");
        Assertions.assertEquals(booksData.getAuthor(), book.getAuthor(), "The author matches");
        Assertions.assertEquals(booksData.getTitle(), book.getTitle(), "The title matches");
        Assertions.assertEquals(booksData.getInfoLink(), book.getInfoLink(), "The infoLink matches");
        Assertions.assertEquals(booksData.getBookId(), book.getBookId(), "The id matches");
        Assertions.assertEquals(booksData.getCategory(), book.getCategory(), "The category matches");
        Assertions.assertEquals(booksData.finishedReading(), book.finishedReading(), "Boolean value matches");
    }

    @Test
    void findById_nullId_throwsNullPointerException() {
        // GIVEN
        String id = randomUUID().toString();

        when(lambdaServiceClient.getBookData(id)).thenReturn(null);

        // THEN
//        Assertions.assertNull(bookService.findById(id), "The example is null when not found");
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findById(id));
    }

    @Test
    void findById_emptyId_throwsNullPointerException() {
        // GIVEN
        String id = "";

        when(lambdaServiceClient.getBookData(id)).thenReturn(any());

        // THEN
//        Assertions.assertNull(bookService.findById(id), "The example is null when not found");
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findById(id));
    }

    @Test
    void getAllBooks() {
        // Given
        Books book1 = new Books(
                "imageLink",
                "category",
                "description",
                "author",
                "title",
                "infoLink",
                true,
                randomUUID().toString());

        Books book2 = new Books(
                "imageLink2",
                "category2",
                "description2",
                "author2",
                "title2",
                "infoLink2",
                true,
                randomUUID().toString());

        Set<Books> booksSet = new HashSet<>();
        booksSet.add(book1);
        booksSet.add(book2);

        //TODO Figure out why IntelliJ is being mean to me
        when(bookRepository.findAll()).thenReturn(booksSet);

        // WHEN
        Set<Books> result = bookService.getAllBooks();

        // THEN
        for (Books book : result) {
            if (Objects.equals(book.getBookId(), book1.getBookId())) {
                Assertions.assertEquals(book1.finishedReading(), book.finishedReading(), "The boolean values match");
                Assertions.assertEquals(book1.getAuthor(), book.getAuthor(), "The authors match");
                Assertions.assertEquals(book1.getCategory(), book.getCategory(), "The category matches");
                Assertions.assertEquals(book1.getDescription(), book.getDescription(), "The description matches");
                Assertions.assertEquals(book1.getTitle(), book.getTitle(), "The title matches");
                Assertions.assertEquals(book1.getImageLink(), book.getImageLink(), "The image link matches");
                Assertions.assertEquals(book1.getInfoLink(), book.getInfoLink(), "The info link matches");
            } else if (Objects.equals(book.getBookId(), book2.getBookId())) {
                Assertions.assertEquals(book2.finishedReading(), book.finishedReading(), "The boolean values match");
                Assertions.assertEquals(book2.getAuthor(), book.getAuthor(), "The authors match");
                Assertions.assertEquals(book2.getCategory(), book.getCategory(), "The category matches");
                Assertions.assertEquals(book2.getDescription(), book.getDescription(), "The description matches");
                Assertions.assertEquals(book2.getTitle(), book.getTitle(), "The title matches");
                Assertions.assertEquals(book2.getImageLink(), book.getImageLink(), "The image link matches");
                Assertions.assertEquals(book2.getInfoLink(), book.getInfoLink(), "The info link matches");
            } else {
                Assertions.assertTrue(false, "book returned that was not in the records!");
            }
        }
        Assertions.assertNotNull(result, "Books set is returned");
        Assertions.assertEquals(2, result.size(), "There are two books in set");
    }


}
