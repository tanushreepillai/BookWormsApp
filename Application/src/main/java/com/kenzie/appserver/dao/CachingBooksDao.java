package com.kenzie.appserver.dao;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.BooksDao;

public class CachingBooksDao {

    private final LoadingCache<String, Books> bookCache;

    public CachingBooksDao(BooksDao booksDao) {
        bookCache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(booksDao::getBook));
    }

    public Books getBook(String id) {
        return bookCache.getUnchecked(id);

    }

}