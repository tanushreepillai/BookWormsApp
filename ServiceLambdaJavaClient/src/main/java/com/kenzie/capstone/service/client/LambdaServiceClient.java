package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.dao.CachingBooksDao;
import com.kenzie.capstone.service.model.BooksData;

import java.util.Collections;
import java.util.Set;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{url}";
    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;
    private CachingBooksDao cachingBooksDao;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public Set<BooksData> getBookData(String url) {

        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{url}", url));
        Set<BooksData> booksData;

        try {
            booksData = Collections.singleton(mapper.readValue(response, BooksData.class));
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return booksData;
    }

//    public BooksData setBooksData(String data) {
//        EndpointUtility endpointUtility = new EndpointUtility();
//        String response = endpointUtility.postEndpoint(SET_BOOK_ENDPOINT, data);
//        BooksData booksData;
//        try {
//            booksData = mapper.readValue(response, BooksData.class);
//        } catch (Exception e) {
//            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//        return booksData;
//    }
}
