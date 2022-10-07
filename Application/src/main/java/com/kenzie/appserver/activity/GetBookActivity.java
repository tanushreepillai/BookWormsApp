package com.kenzie.appserver.activity;

import com.kenzie.appserver.dao.BooksDao;

import javax.inject.Inject;

public class GetBookActivity {
    private final BooksDao booksDao;

    @Inject
    public GetBookActivity(final BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    public Books handleRequest(String id) {
        return booksDao.getBook(id);
    }
}
