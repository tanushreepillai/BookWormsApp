package com.kenzie.capstone.service.dao;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksRecord;

import javax.inject.Inject;
import java.util.List;

public class CachingBooksDao {

    private final LoadingCache<String, List<BooksRecord>> bookCache;

    @Inject
    public CachingBooksDao(BooksDao booksDao) {
        bookCache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(booksDao::getBooksData));
    }

    public List<BooksRecord> getBooksData(String id) {
        return bookCache.getUnchecked(id);
    }

}
