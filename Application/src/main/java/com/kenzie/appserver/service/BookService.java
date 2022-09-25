package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BookRecord;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.ExampleData;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;
    private LambdaServiceClient lambdaServiceClient;

    public BookService(BookRepository bookRepository, LambdaServiceClient lambdaServiceClient) {
        this.bookRepository = bookRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public Books findById(String id) {

        if (id == null || id.isEmpty()) {
            throw new NullPointerException("Invalid/Empty Id");
        }
        // Getting data from the lambda
        BooksData dataFromLambda = lambdaServiceClient.getBookData(id);

        return new Books(dataFromLambda.getImageLink(),dataFromLambda.getCategory(),dataFromLambda.getDescription(),
        dataFromLambda.getAuthor(), dataFromLambda.getTitle(),dataFromLambda.getInfoLink(),dataFromLambda.finishedReading(),
                dataFromLambda.getBookId());

        // Example getting data from the local repository
        /*Books dataFromDynamo = bookRepository
                .findById(id)
                .map(book -> new Books(book.getImageLinks(),book.getCategories(),book.getDescription(),book.getAuthors(),
                        book.getTitle(),book.getInfoLink(),book.isCompleted())
                .orElse(null);*/
        //return dataFromDynamo;
    }

    public Books addBook(Books book) {

        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(book.getBookId());
        bookRecord.setImageLink(book.getImageLink());
        bookRecord.setDescription(book.getDescription());
        bookRecord.setCategories(book.getCategory());
        bookRecord.setAuthor(book.getAuthor());
        bookRecord.setTitle(book.getTitle());
        bookRepository.save(bookRecord);

        return book;
//
//        // Example sending data to the lambda
//        //BooksData dataFromLambda = lambdaServiceClient.setBooksData(id);
//
//        exampleRecord.setName(dataFromLambda.getData());
//        bookRepository.save(bookRecord);

//        Example example = new Example(dataFromLambda.getId(), name);
//        return example;
    }

    public Set<Books> getAllBooks() {
        Set<Books> allBooks = new HashSet<>();
        bookRepository
                .findAll()
                .forEach(book -> allBooks.add(new Books(book.getImageLink(),
                        book.getCategory(), book.getDescription(), book.getAuthor(),book.getTitle(),
                        book.getInfoLink(),book.finishedReading(),book.getId())));

        if (allBooks.isEmpty()) {
            throw new NullPointerException("Empty Set of books");
        }

        return allBooks;
    }
    public void deleteBook(String bookId) {
        if (bookId == null || bookId.isEmpty()) {
            throw new NullPointerException("Invalid/Empty Id");
        }

        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(bookId);
        //cache.evict(bookId);
        bookRepository.delete(bookRecord);

    }
    public void updateBook(Books book) {
        if (bookRepository.existsById(book.getBookId())) {
            BookRecord bookRecord = new BookRecord();
            bookRecord.setBookId(book.getBookId());
            bookRecord.setAuthor(book.getAuthor());
            bookRecord.setCategories(book.getCategory());
            bookRecord.setDescription(book.getDescription());
            bookRecord.setImageLink(book.getImageLink());
            bookRecord.setInfoLink(book.getInfoLink());
            bookRecord.setTitle(book.getTitle());
            bookRecord.finishedReading(true);
            bookRepository.save(bookRecord);
           // cache.evict(book.getBookId());
        }
    }
}
