package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.LambdaBooksDao;
import com.kenzie.capstone.service.model.BooksRecord;
import com.kenzie.capstone.service.model.BooksResponse;
import com.kenzie.capstone.service.utilities.BooksResponseConverter;

import javax.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class LambdaService {

    private final LambdaBooksDao lambdaBooksDao;


//    public LambdaService() {}

    @Inject
    public LambdaService(LambdaBooksDao lambdaBooksDao) {
        this.lambdaBooksDao = lambdaBooksDao;
    }

    public BooksResponse getBookData(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // making response into proper response body
        BooksResponseConverter converter = new BooksResponseConverter();
        BooksResponse convertedResult = converter.convert(response);
        if (convertedResult != null) {
            return convertedResult;
        }
        return null;
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
//
//       List<BooksRecord> books = BooksDao.getBookData(url);
//            if (books.size() > 0) {
//                BooksRecord booksRecord = books.get(0);
//                return new BooksData(booksRecord.getImageLink(),booksRecord.getDescription(),booksRecord.getAuthor(),
//                    booksRecord.getTitle(),booksRecord.getBookId(), booksRecord.isCompleted());
//        }
}
