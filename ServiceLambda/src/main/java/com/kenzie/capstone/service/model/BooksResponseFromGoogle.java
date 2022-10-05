package com.kenzie.capstone.service.model;

import java.util.Set;

public class BooksResponseFromGoogle {
    private Set<LambdaBooksRecord> books;

    public BooksResponseFromGoogle(Set<LambdaBooksRecord> books) {
        this.books = books;
    }

    public int size() {
        return books.size();
    }

    public Set<LambdaBooksRecord> getBooks() {
        return books;
    }
}