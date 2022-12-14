package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.BooksDao;
import com.kenzie.appserver.dao.CachingBooksDao;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BooksRecord;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import java.util.*;

import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;
    private LambdaServiceClient lambdaServiceClient;


    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        lambdaServiceClient = mock(LambdaServiceClient.class);
        bookService = new BookService(bookRepository, lambdaServiceClient);
        initMocks(this);

    }
    /** ------------------------------------------------------------------------
     *  bookService.findByGoogle
     *  ------------------------------------------------------------------------ **/

    @Test
    void findByGoogle() {
        // GIVEN
        String searchRequest = randomUUID().toString();
        String dataFromLambda = randomUUID().toString();

        // WHEN
        when(lambdaServiceClient.getBookData(searchRequest)).thenReturn(dataFromLambda);
        String dataReturned = bookService.findByGoogle(searchRequest);

        // THEN
        verify(lambdaServiceClient).getBookData(searchRequest);
        Assertions.assertNotNull(dataFromLambda, "The data from Lambda is returned");
        Assertions.assertEquals(dataFromLambda,dataReturned, "The data from lambda should be returned");
    }

    @Test
    void findByGoogle_nullSearchRequest_throwsNullPointerException() {
        // GIVEN
        String searchRequest = null;


        // WHEN AND THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findByGoogle(searchRequest));
    }

    @Test
    void findByGoogle_emptySearchRequestString_throwsNullPointerException() {
        // GIVEN
        String searchRequest = "";

        // WHEN AND THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.findByGoogle(searchRequest));
    }

    /** ------------------------------------------------------------------------
     *  bookService.findByDynamoDB
     *  ------------------------------------------------------------------------ **/
    @Test
    void findByDynamoDB() {
        //GIVEN
        String id = randomUUID().toString();
        Books book = new Books("imageLink","description","author", "title",
                true,id);
        BooksRecord booksRecord = new BooksRecord();
        booksRecord.setBookId(id);
        booksRecord.setDescription(book.getDescription());
        booksRecord.setTitle(book.getTitle());
        booksRecord.setImageLink(book.getImageLink());
        booksRecord.setAuthor(book.getAuthor());

        when(bookRepository.findById(id)).thenReturn(Optional.of(booksRecord));

        //WHEN
        Books returnedBook = bookService.findByDynamoDB(id);

       //THEN
        Assertions.assertNotNull(book,"The book is returned");
        Assertions.assertEquals(book.getBookId(),returnedBook.getBookId(),"The book Ids are matching");
        Assertions.assertEquals(book.getAuthor(),returnedBook.getAuthor(),"the author is matching");
        Assertions.assertEquals(book.getTitle(),returnedBook.getTitle(),"the title is matching");
        Assertions.assertEquals(book.getImageLink(),returnedBook.getImageLink(),"the image is matching");
        Assertions.assertEquals(book.getDescription(),returnedBook.getDescription(),"the description is matching");
    }

    /** ------------------------------------------------------------------------
     *  bookService.addBook
     *  ------------------------------------------------------------------------ **/
    @Test
    void addBook() {
        // GIVEN
        String bookId = randomUUID().toString();
        Books book = new Books("imageLink","description","author", "title",
                false,bookId);
        ArgumentCaptor<BooksRecord> bookRecordCaptor = ArgumentCaptor.forClass(BooksRecord.class);

        // WHEN
        Books returnedBooks = bookService.addBook(book);

        // THEN
        Assertions.assertNotNull(returnedBooks);
        verify(bookRepository).save(bookRecordCaptor.capture());
        BooksRecord record = bookRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The clothing record is returned");
        Assertions.assertEquals(record.getBookId(), book.getBookId(), "The book id matches");
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
        BooksRecord bookRecord1 = new BooksRecord();
        bookRecord1.setBookId(book.getBookId());
        bookRecord1.setImageLink(book.getImageLink());
        bookRecord1.setDescription(book.getDescription());
        bookRecord1.setAuthor(book.getAuthor());
        bookRecord1.setTitle(book.getTitle());
        ArgumentCaptor<BooksRecord> bookRecordCaptor = ArgumentCaptor.forClass(BooksRecord.class);
        bookService.addBook(book);
        List<BooksRecord> bookRecordList = new ArrayList<>();
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
        BooksRecord bookRecord1 = new BooksRecord();
        bookRecord1.setBookId(book1.getBookId());
        bookRecord1.setImageLink(book1.getImageLink());
        bookRecord1.setDescription(book1.getDescription());
        bookRecord1.setAuthor(book1.getAuthor());
        bookRecord1.setTitle(book1.getTitle());

        BooksRecord bookRecord2 = new BooksRecord();
        bookRecord2.setBookId(book2.getBookId());
        bookRecord2.setImageLink(book2.getImageLink());
        bookRecord2.setDescription(book2.getDescription());
        bookRecord2.setAuthor(book2.getAuthor());
        bookRecord2.setTitle(book2.getTitle());

        List<BooksRecord> bookRecordList = new ArrayList<>();
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
                Assertions.assertEquals(book1.getFinishedReading(), book.getFinishedReading(), "The boolean values match");
                Assertions.assertEquals(book1.getAuthor(), book.getAuthor(), "The authors match");
                Assertions.assertEquals(book1.getDescription(), book.getDescription(), "The description matches");
                Assertions.assertEquals(book1.getTitle(), book.getTitle(), "The title matches");
                Assertions.assertEquals(book1.getImageLink(), book.getImageLink(), "The image link matches");
            } else if(book.getBookId().equals(book2.getBookId())) {
                Assertions.assertEquals(book2.getFinishedReading(), book.getFinishedReading(), "The boolean values match");
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
    void updateBook() throws Exception {
        // GIVEN
        Books book = new Books(
                "imageLink",
                "description",
                "author",
                "title",
                true,
                randomUUID().toString());

        ArgumentCaptor<BooksRecord> bookRecordCaptor = ArgumentCaptor.forClass(BooksRecord.class);

        // WHEN
        when(bookRepository.existsById(book.getBookId())).thenReturn(true);
        bookService.updateBook(book);

        // THEN
        verify(bookRepository).save(bookRecordCaptor.capture());
        BooksRecord record = bookRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The concert record is returned");
        Assertions.assertEquals(record.getFinishedReading(), book.getFinishedReading(), "The boolean values match");
        Assertions.assertEquals(record.getAuthor(), book.getAuthor(), "The authors match");
        Assertions.assertEquals(record.getDescription(), book.getDescription(), "The description matches");
        Assertions.assertEquals(record.getTitle(), book.getTitle(), "The title matches");
        Assertions.assertEquals(record.getImageLink(), book.getImageLink(), "The image link matches");
    }

    @Test
    void updateBook_nullBook_throwsException() {
        // WHEN & THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.updateBook(null));
    }

    @Test
    void updateBook_invalidBookId_throwsException() {
        // GIVEN
        Books book = new Books(
                "imageLink",
                "description",
                "author",
                "title",
                true,
                randomUUID().toString());

        // WHEN-THEN
        Assertions.assertThrows(NullPointerException.class, () -> bookService.updateBook(book));
    }


    /** ------------------------------------------------------------------------
     *  bookService.deleteBook
     *  ------------------------------------------------------------------------ **/
    @Test
    void deleteBook() {
        String bookId = randomUUID().toString();
        Books book = new Books("imageLink", "description", "author", "title",
                false,bookId);
        bookService.addBook(book);

        // WHEN
        bookService.deleteBook(book.getBookId());

        // THEN
        verify(bookRepository).deleteById(bookId);

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
