package com.kenzie.appserver.controller;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.controller.model.BookCreateRequest;
import com.kenzie.appserver.controller.model.BookResponse;
import com.kenzie.appserver.controller.model.ApplicationLambdaBooksRecord;
import com.kenzie.appserver.service.BookService;

import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.BooksData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private LambdaServiceClient lambdaServiceClient;

    BookController(BookService bookService, LambdaServiceClient lambdaServiceClient) {
        this.bookService = bookService;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") String id) {

        Books book = bookService.findByDynamoDB(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookToBookResponse(book));
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addNewBook(@RequestBody BookCreateRequest bookCreateRequest) {
        String title = bookCreateRequest.getTitle();
        String author = bookCreateRequest.getAuthor();
        // Id is set to be the name of the Title + the first letter of the Author
        String id = title + author.charAt(0);

        Books book = new Books(
                bookCreateRequest.getImageLink(),
                bookCreateRequest.getDescription(),
                bookCreateRequest.getAuthor(),
                bookCreateRequest.getTitle(),
                false,
                id);
        bookService.addBook(book);
        BookResponse response = bookToBookResponse(book);
        return ResponseEntity.created(URI.create("/book/" + response.getBookId()))
                .body(response);

    }

    @GetMapping("/search/{searchRequest}")
    public ResponseEntity<String> searchBooks(@PathVariable("searchRequest") String searchRequest) {
        String returnedBookRecordsFromLambda = lambdaServiceClient.getBookData(searchRequest);

        return ResponseEntity.ok(returnedBookRecordsFromLambda);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<BookResponse>> getAllBooks() {
        Set<Books> allBooks = bookService.getAllBooks();

        Set<BookResponse> responses = allBooks.stream()
                .map(this::bookToBookResponse).collect(Collectors.toSet());

        return ResponseEntity.ok(responses);
    }

    @PutMapping
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookCreateRequest bookUpdateRequest) {
        Books book = new Books(bookUpdateRequest.getImageLink(),
                bookUpdateRequest.getDescription(),
                bookUpdateRequest.getAuthor(),
                bookUpdateRequest.getTitle(),
                bookUpdateRequest.finishedReading(),
                bookUpdateRequest.getBookId());

        return ResponseEntity.ok(bookToBookResponse(book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable("bookId") String id) {
        Books book = bookService.findByDynamoDB(id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private BookResponse bookToBookResponse(Books book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(book.getTitle());
        bookResponse.setBookId(book.getBookId());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setFinishedReading(book.getFinishedReading());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setImageLink(book.getImageLink());

        return bookResponse;
    }

    // for LAMBDA
    private BookResponse booksDataToBookResponse(BooksData booksData) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(booksData.getVolumeInfo().getTitle());
//        bookResponse.setBookId(booksData.getBookId());
        bookResponse.setAuthor(booksData.getVolumeInfo().getAuthor().toString());
//        bookResponse.finishedReading(booksData.finishedReading());
        bookResponse.setDescription(booksData.getVolumeInfo().getDescription());
        bookResponse.setImageLink(booksData.getVolumeInfo().getImageLinks().getSmallThumbnail());

        return bookResponse;
    }
}
