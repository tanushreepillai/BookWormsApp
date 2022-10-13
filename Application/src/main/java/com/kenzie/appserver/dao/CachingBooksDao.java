package com.kenzie.appserver.dao;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.dao.BooksDao;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CachingBooksDao {

    private final LoadingCache<String, Set<Books>> bookCache; // return all books in the cache (values)

                                // Set<Books> => only want to return the first item

    @Inject
    public CachingBooksDao(BooksDao booksDao) {
        bookCache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(booksDao::getBooks));
    }

    //    public Books getBook(String id) { // title+author.charAt(0)
    //        return bookCache.getUnchecked(id);
    //    }

    // getBook method: helper method for getBooks

    public Set<Books> getBooks() {
        Set<Set<Books>> bookSet = bookCache.asMap().values().stream().collect(Collectors.toSet());
        Optional<Set<Books>> firstElements = bookSet.stream().findFirst();
        if (firstElements.isPresent()) {
            return firstElements.get();
        }
        return new HashSet<>();
    }
}