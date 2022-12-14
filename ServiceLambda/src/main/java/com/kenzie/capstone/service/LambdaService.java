package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.LambdaBooksDao;

import javax.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LambdaService {

    private final LambdaBooksDao lambdaBooksDao;


//    public LambdaService() {}

    @Inject
    public LambdaService(LambdaBooksDao lambdaBooksDao) {
        this.lambdaBooksDao = lambdaBooksDao;
    }

    public HttpResponse<String> getBookData(String searchRequest) throws IOException, InterruptedException {
//       List<BooksRecord> books = BooksDao.getBookData(url);
//            if (books.size() > 0) {
//                BooksRecord booksRecord = books.get(0);
//                return new BooksData(booksRecord.getImageLink(),booksRecord.getDescription(),booksRecord.getAuthor(),
//                    booksRecord.getTitle(),booksRecord.getBookId(), booksRecord.isCompleted());
//        }
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + searchRequest +
                "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";

        url = url.replaceAll(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
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
