package com.kenzie.appserver.service;

import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.CachingBooksDao;
import com.kenzie.appserver.repositories.BookRepository;
import com.kenzie.appserver.repositories.model.BooksRecord;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.BooksData;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;
    private LambdaServiceClient lambdaServiceClient;
    private final CachingBooksDao cachingBooksDao;

    public BookService(BookRepository bookRepository,
                       LambdaServiceClient lambdaServiceClient,
                       CachingBooksDao cachingBooksDao) {
        this.bookRepository = bookRepository;
        this.lambdaServiceClient = lambdaServiceClient;
        this.cachingBooksDao = cachingBooksDao;
    }

    public Set<Books> findByGoogle(String url) {

        if (url == null || url.isEmpty()) {
            throw new NullPointerException("Cannot find book in Google API");
        }

        // Getting data from the lambda
        Set<BooksData> dataFromLambda = lambdaServiceClient.getBookData(url);
        Set<Books> booksSetFromGoogle = new HashSet<>();

        for (BooksData book : dataFromLambda) {
            Books book1 = new Books(book.getImageLinks().getSmallThumbnail(), book.getVolumeInfo().getDescription(), book.getVolumeInfo().getAuthor().toString(), book.getVolumeInfo().getTitle(), false, null);
            booksSetFromGoogle.add(book1);
        }

        return booksSetFromGoogle;
    }

    public Books findByDynamoDB(String id) {
        Books cachedBook = cachingBooksDao.getBook(id);
        if (cachedBook != null) {
            return cachedBook;
        }
        Books bookFromDynamoDB = bookRepository
                .findById(id)
                .map(book -> new Books(book.getImageLink(),
                        book.getDescription(),
                        book.getAuthor(),
                        book.getTitle(),
                        book.finishedReading(),
                        book.getId()))
                .orElse(null);

        if (bookFromDynamoDB != null) {
            //We need to have an add method in cachingDao to add the book once it is retrieved from DynamoDB.
            //cachingBooksDao.add(bookFromDynamoDB.getBookId(), bookFromDynamoDB);
        }
        return bookFromDynamoDB;

    }
    public Books addBook(Books book) {

        BooksRecord bookRecord = new BooksRecord();
        bookRecord.setBookId(book.getBookId());
        bookRecord.setImageLink(book.getImageLink());
        bookRecord.setDescription(book.getDescription());
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
                        book.getDescription(), book.getAuthor(),book.getTitle(),
                        book.finishedReading(),book.getId())));

        if (allBooks.isEmpty()) {
            throw new NullPointerException("Empty Set of books");
        }

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
            bookRecord.finishedReading(true);
            bookRepository.save(bookRecord);
           // cache.evict(book.getBookId());
        }
    }
}
