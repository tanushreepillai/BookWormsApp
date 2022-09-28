package com.kenzie.appserver.controller;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.controller.model.BookCreateRequest;
import com.kenzie.appserver.controller.model.BookResponse;
import com.kenzie.appserver.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") String id) {

        Books book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookToBookResponse(book));
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addNewBook(@RequestBody BookCreateRequest bookCreateRequest) {
        System.out.println("inside books/add");
        Books book = new Books(bookCreateRequest.getImageLink(),
                bookCreateRequest.getDescription(),
                bookCreateRequest.getAuthor(),
                bookCreateRequest.getTitle(),
                bookCreateRequest.getInfoLink(),
                bookCreateRequest.finishedReading(),
                bookCreateRequest.getBookId());
        bookService.addBook(book);

        BookResponse response = bookToBookResponse(book);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<BookResponse>> getAllBooks() {
        System.out.println("inside getAllBooks in controller");
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
                bookUpdateRequest.getInfoLink(),
                bookUpdateRequest.finishedReading(),
                bookUpdateRequest.getBookId());

        return ResponseEntity.ok(bookToBookResponse(book));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable String id) {
        Books book = bookService.findById(id);
        bookService.deleteBook(id);

        return ResponseEntity.ok().body(bookToBookResponse(book));
    }

    private BookResponse bookToBookResponse(Books book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setAuthors(book.getAuthor());
        bookResponse.finishedReading(book.finishedReading());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setImageLink(book.getImageLink());
        bookResponse.setInfoLink(book.getInfoLink());

        return bookResponse;
    }
}
