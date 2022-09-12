package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Book;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BookRecord;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.capstone.service.client.LambdaServiceClient;
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

    /*public Example findById(String id) {

        // Example getting data from the lambda
        ExampleData dataFromLambda = lambdaServiceClient.getExampleData(id);

        // Example getting data from the local repository
        Example dataFromDynamo = bookRepository
                .findById(id)
                .map(example -> new Example(example.getId(), example.getName()))
                .orElse(null);

        return dataFromDynamo;
    }

    public Example addBook(String name) {
        // Example sending data to the lambda
        ExampleData dataFromLambda = lambdaServiceClient.setExampleData(name);

        // Example sending data to the local repository
        BookRecord bookRecord = new BookRecord();
        exampleRecord.setId(dataFromLambda.getId());
        exampleRecord.setName(dataFromLambda.getData());
        bookRepository.save(bookRecord);

        Example example = new Example(dataFromLambda.getId(), name);
        return example;
    }*/

    public Set<Book> getAllBooks() {
        Set<Book> allBooks = new HashSet<>();
        bookRepository
                .findAll()
                .forEach(book -> allBooks.add(new Book(book.getImageLinks(),
                        book.getCategories(), book.getDescription(), book.getAuthors(),book.getTitle(),
                        book.getInfoLink(),book.isCompleted(),book.getId())));

        return allBooks;
    }
    public void deleteBook(String bookId) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(bookId);
        //cache.evict(bookId);
        bookRepository.delete(bookRecord);

    }
    public void updateBook(Book book) {
        if (bookRepository.existsById(book.getBookId())) {
            BookRecord bookRecord = new BookRecord();
            bookRecord.setBookId(book.getBookId());
            bookRecord.setAuthors(book.getAuthors());
            bookRecord.setCategories(book.getCategories());
            bookRecord.setDescription(book.getDescription());
            bookRecord.setImageLinks(book.getImageLinks());
            bookRecord.setInfoLink(book.getInfoLink());
            bookRecord.setTitle(book.getTitle());
            bookRecord.setCompleted(true);
            bookRepository.save(bookRecord);
           // cache.evict(book.getBookId());
        }
    }
}
