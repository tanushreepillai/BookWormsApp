package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksListDTO;
import com.kenzie.capstone.service.model.models.Books;

import java.util.Set;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{url}";
//    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public Set<BooksData> getBookData(String url) {

        EndpointUtility endpointUtility = new EndpointUtility();

        String response = endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{url}", url));

        BooksListDTO booksDTOList;

        try {
            booksDTOList = mapper.readValue(response, BooksListDTO.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        Set<BooksData> booksData;

        BooksData book;

        booksDTOList.getItems().forEach(item ->
                book;
        );


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
