package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class BooksResponseFromGoogle {
     @JsonProperty("items")
    private Set<BooksData> books;

    public BooksResponseFromGoogle(Set<BooksData> books) {
        this.books = books;
    }

    public int size() {
        return books.size();
    }

    public Set<BooksData> getBooks() {
        return books;
    }

}