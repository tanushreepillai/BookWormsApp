package com.kenzie.appserver.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.backend.models.BooksListDTO;
import com.kenzie.appserver.backend.models.Items;

import java.util.List;

public class GetBooks {

    public String buildURL(String title, String author) {
        StringBuilder sb = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=");
        String key = "&key=AIzaSyAmwU-FhO1HLhFjungcYPqfxr7jAbk5faE";

        sb.append(sbHelper(title));

        sb.append("+inauthor:");

        sb.append(sbHelper(author)).append(key);

        return  sb.toString();
    }

    public List<Items> getBooks(String title, String author) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String httpResponse = CustomHttpClient.sendGET(buildURL(title, author));
        BooksListDTO booksDTOList = objectMapper.readValue(httpResponse, BooksListDTO.class);
        return booksDTOList.getItems();
    }

    private StringBuilder sbHelper(String param) {
        StringBuilder sb = new StringBuilder();
        List<String> array = List.of(param.split(" "));
        for (int i=0; i<array.size(); i++) {
            if (i != array.size()-1) {
                sb.append(array.get(i)).append("%20");
            } else {
                sb.append(array.get(i));
            }
        }

        return sb;
    }
}
