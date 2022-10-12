package com.kenzie.appserver.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.repositories.model.BooksRecord;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BooksDao {
    private DynamoDBMapper mapper;
    @Inject
    public BooksDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Books getBook(String id) {
        BooksRecord bookRecord = new BooksRecord();
        bookRecord.setBookId(id);
        bookRecord = mapper.load(bookRecord);
        return new Books(bookRecord.getImageLink(),
                bookRecord.getDescription(),
                bookRecord.getAuthor(),
                bookRecord.getTitle(),
                bookRecord.getFinishedReading(),
                id);
    }

    public Set<Books> getBooks() {
        Set<BooksRecord> records = mapper.scan(BooksRecord.class, new DynamoDBScanExpression()).stream().collect(Collectors.toSet());
        Set<Books> returnedBooks = new HashSet<>();
        for (BooksRecord bookRecord : records) {
            new Books(bookRecord.getImageLink(),
                    bookRecord.getDescription(),
                    bookRecord.getAuthor(),
                    bookRecord.getTitle(),
                    bookRecord.getFinishedReading(),
                    bookRecord.getBookId());
        }
        return returnedBooks;
        //        Books book = new Books(); // for selecting a book with particular attributes
        //            song.setArtist(artist);
        //        DynamoDBQueryExpression<Song> queryExpression = new DynamoDBQueryExpression<Song>()
        //                .withHashKeyValues(song);
        //        PaginatedQueryList<Song> songList = mapper.query(Song.class, queryExpression);
        //            return songList;
    }


}
