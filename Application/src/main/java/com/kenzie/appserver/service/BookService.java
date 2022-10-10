package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.CachingBooksDao;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BooksRecord;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;
    private LambdaServiceClient lambdaServiceClient;
    private final CachingBooksDao cachingBooksDao;

    public BookService(BookRepository bookRepository,
                       LambdaServiceClient lambdaServiceClient) {
        this.bookRepository = bookRepository;
        this.lambdaServiceClient = lambdaServiceClient;
        this.cachingBooksDao = null;
    }

    public String findByGoogle(String searchRequest) {

        if (searchRequest == null || searchRequest.isEmpty()) {
            throw new NullPointerException("Cannot find book in Google API");
        }

        // Getting data from the lambda
        String dataFromLambda = lambdaServiceClient.getBookData(searchRequest);

        Set<Books> booksSetFromGoogle = new HashSet<>();

//        for (BooksData book : dataFromLambda) {
//            Books book1 = new Books(book.getVolumeInfo().getImageLinks().getSmallThumbnail(), book.getVolumeInfo().getDescription(), book.getVolumeInfo().getAuthor()[0], book.getVolumeInfo().getTitle(), false, null);
//            booksSetFromGoogle.add(book1);
//        }

        return dataFromLambda;
    }

    public Books findByDynamoDB(String id) {
        return cachingBooksDao.getBook(id);
    }

    public Books addBook(Books book) {

        BooksRecord bookRecord = new BooksRecord();
        bookRecord.setBookId(book.getBookId());
        bookRecord.setImageLink(book.getImageLink());
        bookRecord.setDescription(book.getDescription());
        bookRecord.setAuthor(book.getAuthor());
        bookRecord.setTitle(book.getTitle());
        bookRecord.setFinishedReading(false);

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
        System.out.println("inside getAllBooks in BookService1");
        Set<Books> allBooks = new HashSet<>();
        bookRepository
                .findAll()
                .forEach(book -> allBooks.add(new Books(book.getImageLink(),
                        book.getDescription(), book.getAuthor(), book.getTitle(),
                        book.getFinishedReading(), book.getBookId())));

        if (allBooks.isEmpty()) {
            throw new NullPointerException("Empty Set of books");
        }
        System.out.println("inside getAllBooks in BookService2");

        return allBooks;
    }
    public void deleteBook(String bookId) {
        if (bookId == null || bookId.isEmpty()) {
            throw new NullPointerException("Invalid/Empty Id");
        }

        BooksRecord bookRecord = new BooksRecord();
        bookRecord.setBookId(bookId);
        //cache.evict(bookId);
        bookRepository.delete(bookRecord);

    }
    public void updateBook(Books book) {
        if (bookRepository.existsById(book.getBookId())) {
            BooksRecord bookRecord = new BooksRecord();
            bookRecord.setBookId(book.getBookId());
            bookRecord.setAuthor(book.getAuthor());
            bookRecord.setDescription(book.getDescription());
            bookRecord.setImageLink(book.getImageLink());
            bookRecord.setTitle(book.getTitle());
            bookRecord.setFinishedReading(true);
            bookRepository.save(bookRecord);
           // cache.evict(book.getBookId());
        }
    }
}
