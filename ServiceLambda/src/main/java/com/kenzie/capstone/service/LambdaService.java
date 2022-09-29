package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksRecord;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.ExampleRecord;

import javax.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LambdaService {

    private final CachingBooksDao cachingBooksDao;

    @Inject
    public LambdaService(CachingBooksDao cachingBooksDao) {
        this.cachingBooksDao = cachingBooksDao;
    }

    public HttpResponse<String> getBookData(String id) throws IOException, InterruptedException {
       /* List<BooksRecord> books = cachingBooksDao.getBooksData(id);
            if (books.size() > 0) {
                BooksRecord booksRecord = books.get(0);
                return new BooksData(booksRecord.getImageLink(),booksRecord.getDescription(),booksRecord.getAuthor(),
                    booksRecord.getTitle(),booksRecord.getInfoLink(),booksRecord.getBookId(), booksRecord.isCompleted());
        }*/

        HttpClient client = HttpClient.newHttpClient();
        String URLString = "https://www.googleapis.com/books/v1/volumes?q=" +
                id +  "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";
        URI uri = URI.create(URLString);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

//        return null;
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
