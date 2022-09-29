package com.kenzie.capstone.service.dao;

import com.kenzie.capstone.service.CacheClient;
import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksRecord;

import javax.inject.Inject;
import java.util.List;

public class CachingBookDao {
    private final BooksDao booksDao;
    private final CacheClient cache;

    @Inject
    public CachingBookDao(BooksDao booksDao, CacheClient cache) {
        this.booksDao = booksDao;
        this.cache = cache;
    }

    public String getBook(String id) {
        String booksData = cache.getValue(id)
//                .map(String::valueOf)
                .orElseGet(() -> booksDao.getBooksData(id).get(0).getTitle());
        cache.setValue(id,300, booksData.toString());
        return booksData;
    }

    public boolean bookInvalidated(String id) {
        return cache.invalidate(id);
    }
}
