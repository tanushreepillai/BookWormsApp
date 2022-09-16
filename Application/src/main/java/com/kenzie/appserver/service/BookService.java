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

        // Example getting data from the lambda
        BooksData dataFromLambda = lambdaServiceClient.getBookData(id);

        // Example getting data from the local repository
        /*Books dataFromDynamo = bookRepository
                .findById(id)
                .map(book -> new Books(book.getImageLinks(),book.getCategories(),book.getDescription(),book.getAuthors(),
                        book.getTitle(),book.getInfoLink(),book.isCompleted())
                .orElse(null);*/
        return new Books(dataFromLambda.getImageLink(),dataFromLambda.getCategory(),dataFromLambda.getDescription(),
        dataFromLambda.getAuthor(), dataFromLambda.getTitle(),dataFromLambda.getInfoLink(),dataFromLambda.finishedReading(),
                dataFromLambda.getBookId());

        //return dataFromDynamo;
    }

//    public Books addBook(String id) {
//        // Example sending data to the lambda
//        //BooksData dataFromLambda = lambdaServiceClient.setBooksData(id);
//
//        // Example sending data to the local repository
//        //TODO: Ask Jacobus about this method. Do we pass in all the book details as parameters?
//        BookRecord bookRecord = new BookRecord();
//        bookRecord.setBookId(dataFromLambda.getBookId());
//        bookRecord.setImageLinks(dataFromLambda.getImageLinks());
//        bookRecord.setDescription(dataFromLambda.getDescription());
//        bookRecord.setCategories(dataFromLambda.getCategories());
//        bookRecord.setAuthors(dataFromLambda.getAuthors());
//        bookRecord.setTitle(dataFromLambda.getTitle());
//
//
//
//        exampleRecord.setName(dataFromLambda.getData());
//        bookRepository.save(bookRecord);
//
//        Example example = new Example(dataFromLambda.getId(), name);
//        return example;
//    }

    public Set<Books> getAllBooks() {
        Set<Books> allBooks = new HashSet<>();
        bookRepository
                .findAll()
                .forEach(book -> allBooks.add(new Books(book.getImageLink(),
                        book.getCategory(), book.getDescription(), book.getAuthor(),book.getTitle(),
                        book.getInfoLink(),book.finishedReading(),book.getId())));

        return allBooks;
    }
    public void deleteBook(String bookId) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(bookId);
        //cache.evict(bookId);
        bookRepository.delete(bookRecord);

    }
    public void updateBook(Books book) {
        if (bookRepository.existsById(book.getBookId())) {
            BookRecord bookRecord = new BookRecord();
            bookRecord.setBookId(book.getBookId());
            bookRecord.setAuthors(book.getAuthor());
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
