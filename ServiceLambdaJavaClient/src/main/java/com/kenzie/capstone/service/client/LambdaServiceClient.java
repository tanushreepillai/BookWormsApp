package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksResponse;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{url}";
//    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public BooksResponse getBookData(String url) {

        EndpointUtility endpointUtility = new EndpointUtility();

        // Else retrieve from DB and return its book data
        String response = endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{url}", url));

        BooksResponse booksResponse; // books response is a set of books

        try {
            // need to change response into our Book Class model so we can
            // return to the front end
            booksResponse = mapper.readValue(response, BooksResponse.class);
            // implement the converter here and return the set of books


        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return booksResponse;
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
