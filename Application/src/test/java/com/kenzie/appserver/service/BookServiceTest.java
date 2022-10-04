package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.CachingBooksDao;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BookRecord;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.BooksData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.verify;

import java.util.*;

import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;
    private LambdaServiceClient lambdaServiceClient;
    private CachingBooksDao cachingBooksDao;

    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        lambdaServiceClient = mock(LambdaServiceClient.class);
        bookService = new BookService(bookRepository, lambdaServiceClient, cachingBooksDao);
        cachingBooksDao = mock(CachingBooksDao.class);
    }
    /** ------------------------------------------------------------------------
     *  bookService.findByGoogle
     *  ------------------------------------------------------------------------ **/

    @Test
    void findByGoogle() {
        // GIVEN
        String url = randomUUID().toString();

        BooksData booksData = new BooksData(
                "imageLink",
                "description",
                "author",
                "title",
                url,
                true);

        // WHEN
        when(lambdaServiceClient.getBookData(url)).thenReturn(booksData);
        Set<Books> book = bookService.findByGoogle(url);
        // todo changed return value from book to Set<Books>

        // THEN
        verify(lambdaServiceClient).getBookData(url);
        Assertions.assertNotNull(book, "The book is returned");
        Assertions.assertEquals(booksData.getImageLink(), book.getImageLink(), "The imageLink matches");
        Assertions.assertEquals(booksData.getDescription(), book.getDescription(), "The description matches");
        Assertions.assertEquals(booksData.getAuthor(), book.getAuthor(), "The author matches");
        Assertions.assertEquals(booksData.getTitle(), book.getTitle(), "The title matches");
        Assertions.assertEquals(booksData.getBookId(), book.getBookId(), "The id matches");
        Assertions.assertEquals(booksData.finishedReading(), book.finishedReading(), "Boolean value matches");
    }

    @Test
    void findByGoogle_nullId_throwsNullPointerException() {
        // GIVEN
        String url = randomUUID().toString();

        when(lambdaServiceClient.getBookData(url)).thenReturn(null);

        // THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findByGoogle(url));
    }

    @Test
    void findByGoogle_emptyId_throwsNullPointerException() {
        // GIVEN
        String id = "";

        when(lambdaServiceClient.getBookData(id)).thenReturn(null);

        // THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findByGoogle(id));
    }
    /** ------------------------------------------------------------------------
     *  bookService.findByDynamoDB
     *  ------------------------------------------------------------------------ **/
    /*@Test
    void findByDynamoDB() {
        //GIVEN
        String id = randomUUID().toString();
        Books book = new Books("imageLink","description","author", "title",
                false,id);

        //WHEN
        //when(bookCache.getUnchecked(id)).thenReturn(book);
        book = cachingBooksDao.getBook(id);

        //THEN
        Assertions.assertNotNull(book, "The book is returned");

    }*/

    /** ------------------------------------------------------------------------
     *  bookService.addBook
     *  ------------------------------------------------------------------------ **/
    @Test
    void addBook() {
        // GIVEN
        String bookId = randomUUID().toString();
        Books book = new Books("imageLink","description","author", "title",
                false,bookId);
        ArgumentCaptor<BookRecord> bookRecordCaptor = ArgumentCaptor.forClass(BookRecord.class);

        // WHEN
        Books returnedBooks = bookService.addBook(book);

        // THEN
        Assertions.assertNotNull(returnedBooks);
        verify(bookRepository).save(bookRecordCaptor.capture());
        BookRecord record = bookRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The clothing record is returned");
        Assertions.assertEquals(record.getId(), book.getBookId(), "The book id matches");
        Assertions.assertEquals(record.getAuthor(), book.getAuthor(), "The author matches");
        Assertions.assertEquals(record.getTitle(), book.getTitle(), "The title matches");
        Assertions.assertEquals(record.getDescription(), book.getDescription(), "The description matches");
        Assertions.assertEquals(record.getImageLink(), book.getImageLink(), "The image matches");
    }

    @Test
    void addBook_bookAlreadyExists() {
        // GIVEN
        String bookId = randomUUID().toString();
        Books book = new Books("imageLink","description","author", "title",
                false,bookId);
        BookRecord bookRecord1 = new BookRecord();
        bookRecord1.setBookId(book.getBookId());
        bookRecord1.setImageLink(book.getImageLink());
        bookRecord1.setDescription(book.getDescription());
        bookRecord1.setAuthor(book.getAuthor());
        bookRecord1.setTitle(book.getTitle());
        ArgumentCaptor<BookRecord> bookRecordCaptor = ArgumentCaptor.forClass(BookRecord.class);
        bookService.addBook(book);
        List<BookRecord> bookRecordList = new ArrayList<>();
        bookRecordList.add(bookRecord1);

        //WHEN - Add the book again
        bookService.addBook(book);
        when(bookRepository.findAll()).thenReturn(bookRecordList);


        // THEN - The book should already exist. So, size of the returned set should be 1
        Set<Books> booksSet = bookService.getAllBooks();
        Assertions.assertEquals(1,booksSet.size());
    }

    /** ------------------------------------------------------------------------
     *  bookService.getAllBooks
     *  ------------------------------------------------------------------------ **/

    @Test
    void getAllBooks() {
        // Given
        Books book1 = new Books(
                "imageLink",
                "description",
                "author",
                "title",
                false,
                randomUUID().toString());

        Books book2 = new Books(
                "imageLink2",
                "description2",
                "author2",
                "title2",
                false,
                randomUUID().toString());
        BookRecord bookRecord1 = new BookRecord();
        bookRecord1.setBookId(book1.getBookId());
        bookRecord1.setImageLink(book1.getImageLink());
        bookRecord1.setDescription(book1.getDescription());
        bookRecord1.setAuthor(book1.getAuthor());
        bookRecord1.setTitle(book1.getTitle());

        BookRecord bookRecord2 = new BookRecord();
        bookRecord2.setBookId(book2.getBookId());
        bookRecord2.setImageLink(book2.getImageLink());
        bookRecord2.setDescription(book2.getDescription());
        bookRecord2.setAuthor(book2.getAuthor());
        bookRecord2.setTitle(book2.getTitle());

        List<BookRecord> bookRecordList = new ArrayList<>();
        bookRecordList.add(bookRecord1);
        bookRecordList.add(bookRecord2);

        when(bookRepository.findAll()).thenReturn(bookRecordList);

        // WHEN
        Set<Books> result = bookService.getAllBooks();

        // THEN
        Assertions.assertNotNull(result, "Books set is returned");
        Assertions.assertEquals(2, result.size(), "There are two books in set");
        for (Books book : result) {
            if (book.getBookId().equals(book1.getBookId())) {
                Assertions.assertEquals(book1.finishedReading(), book.finishedReading(), "The boolean values match");
                Assertions.assertEquals(book1.getAuthor(), book.getAuthor(), "The authors match");
                Assertions.assertEquals(book1.getDescription(), book.getDescription(), "The description matches");
                Assertions.assertEquals(book1.getTitle(), book.getTitle(), "The title matches");
                Assertions.assertEquals(book1.getImageLink(), book.getImageLink(), "The image link matches");
            } else if(book.getBookId().equals(book2.getBookId())) {
                Assertions.assertEquals(book2.finishedReading(), book.finishedReading(), "The boolean values match");
                Assertions.assertEquals(book2.getAuthor(), book.getAuthor(), "The authors match");
                Assertions.assertEquals(book2.getDescription(), book.getDescription(), "The description matches");
                Assertions.assertEquals(book2.getTitle(), book.getTitle(), "The title matches");
                Assertions.assertEquals(book2.getImageLink(), book.getImageLink(), "The image link matches");
            } else {
                Assertions.assertTrue(false, "book returned that was not in the records!");
            }
        }

    }

    @Test
    void getAllBooks_emptySet_throwsNullPointerException() {
        // WHEN & THEN
        when((bookRepository.findAll())).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> bookService.getAllBooks());
    }

    /** ------------------------------------------------------------------------
     *  bookService.updateBook
     *  ------------------------------------------------------------------------ **/

    @Test
    void updateBook() {
        // GIVEN
        Books book = new Books(
                "imageLink",
                "description",
                "author",
                "title",
                true,
                randomUUID().toString());

        ArgumentCaptor<BookRecord> bookRecordCaptor = ArgumentCaptor.forClass(BookRecord.class);

        // WHEN
        when(bookRepository.existsById(book.getBookId())).thenReturn(true);
        bookService.updateBook(book);

        // THEN
        verify(bookRepository).save(bookRecordCaptor.capture());
        BookRecord record = bookRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The concert record is returned");
        Assertions.assertEquals(record.finishedReading(), book.finishedReading(), "The boolean values match");
        Assertions.assertEquals(record.getAuthor(), book.getAuthor(), "The authors match");
        Assertions.assertEquals(record.getDescription(), book.getDescription(), "The description matches");
        Assertions.assertEquals(record.getTitle(), book.getTitle(), "The title matches");
        Assertions.assertEquals(record.getImageLink(), book.getImageLink(), "The image link matches");
    }


    /** ------------------------------------------------------------------------
     *  bookService.deleteBook
     *  ------------------------------------------------------------------------ **/
    @Test
    void deleteBook() {
        Books book = new Books(
                "imageLink",
                "description",
                "author",
                "title",
                true,
                randomUUID().toString());

        ArgumentCaptor<BookRecord> bookRecordCaptor = ArgumentCaptor.forClass(BookRecord.class);

        bookService.addBook(book);

        // WHEN
        bookService.deleteBook(book.getBookId());

        // THEN
        verify(bookRepository).delete(bookRecordCaptor.capture());
    }

    @Test
    void deleteBook_nullId_throwsNullPointerException() {
        // WHEN & THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.deleteBook(null));
    }

    @Test
    void deleteBook_emptyId_throwsNullPointerException() {
        // WHEN & THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.deleteBook(""));
    }
}
