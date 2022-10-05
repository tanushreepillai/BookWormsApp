package com.kenzie.capstone.service.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.model.BooksResponse;

import java.net.http.HttpResponse;

public class BooksResponseConverter {
    BooksResponse response;
    // convert BooksResponse to BooksRecord

    public BooksResponse convert(HttpResponse<String> gson) throws Exception {
        // need GSON converter
        Gson gsonBuilder = new GsonBuilder().create();
        try {
            return gsonBuilder.fromJson(gson.body(), BooksResponse.class); // a set of BooksRecord
        } catch(Exception ex) {
            // later on create new custom exception
            // to improve project
            throw new Exception("API data to app data conversion not working");
        }
    }
}
