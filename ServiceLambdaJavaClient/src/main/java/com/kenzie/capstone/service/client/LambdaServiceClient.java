package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.dao.CachingBooksDao;
import com.kenzie.capstone.service.model.BooksData;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{id}";
    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;
    private CachingBooksDao cachingBooksDao;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public BooksData getBookData(String id) {

        EndpointUtility endpointUtility = new EndpointUtility();

        // If book data is present in cache, return that cache value
        

        // Else retrieve from DB and return its book data
        String response = endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{id}", id));
        BooksData booksData;
        try {
            booksData = mapper.readValue(response, BooksData.class);
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
