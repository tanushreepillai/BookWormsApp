package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksRecord;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.ExampleRecord;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LambdaService {

    private final BooksDao booksDao;

    @Inject
    public LambdaService(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    public BooksData getBooksData(String id) {
        List<BooksRecord> books = booksDao.getBooksData(id);
        if (books.size() > 0) {
            BooksRecord booksRecord = books.get(0);
            return new BooksData(booksRecord.getImageLinks(),booksRecord.getDescription(),booksRecord.getAuthors(),
                    booksRecord.getTitle(),booksRecord.getInfoLink(),booksRecord.getBookId());
            }
        return null;
    }

//    public ExampleData getExampleData(String id) {
//        List<ExampleRecord> records = exampleDao.getExampleData(id);
//        if (records.size() > 0) {
//            return new ExampleData(records.get(0).getId(), records.get(0).getData());
//        }
//        return null;
//    }

//    public ExampleData setExampleData(String data) {
//        String id = UUID.randomUUID().toString();
//        ExampleRecord record = exampleDao.setExampleData(id, data);
//        return new ExampleData(id, data);
//    }
}
