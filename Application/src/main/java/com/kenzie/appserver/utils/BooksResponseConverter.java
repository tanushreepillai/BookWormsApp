package com.kenzie.appserver.utils;

import com.kenzie.appserver.repositories.model.BooksRecord;
import com.kenzie.appserver.repositories.model.BooksResponse;

public class BooksResponseConverter {
    BooksResponse response;
    // convert BooksResponse to BooksRecord

    public BooksResponse convert(String gson) {
        // need GSON converter
        Gson gson = new GsonBuilder();
    }
}
