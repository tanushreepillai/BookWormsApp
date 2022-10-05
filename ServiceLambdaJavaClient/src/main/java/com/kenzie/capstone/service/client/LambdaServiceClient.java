package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.LambdaBooksRecord;
import com.kenzie.capstone.service.model.BooksResponseFromGoogle;
import com.kenzie.capstone.service.utilities.BooksResponseConverter;

import java.util.Set;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{url}";
//    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public Set<LambdaBooksRecord> getBookData(String url) {

        EndpointUtility endpointUtility = new EndpointUtility();

        // Else retrieve from DB and return its book data
        String response = endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{url}", url));

        try {
            // need to change response into our Book Class model so we can
            // return to the front end
            BooksResponseFromGoogle booksResponseFromGoogle = mapper.readValue(response, BooksResponseFromGoogle.class);
            BooksResponseConverter converter = new BooksResponseConverter();
            Set<LambdaBooksRecord> booksResponse = converter.convert(booksResponseFromGoogle);
            return booksResponse;

        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
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
