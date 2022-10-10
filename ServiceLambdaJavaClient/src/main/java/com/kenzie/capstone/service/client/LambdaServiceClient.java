package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.LambdaBooksRecord;
import com.kenzie.capstone.service.model.BooksResponseFromGoogle;
import com.kenzie.capstone.service.utilities.BooksResponseConverter;
import org.apache.commons.collections4.Get;

import java.util.Set;


public class LambdaServiceClient {

    private static final String GET_BOOK_ENDPOINT = "books/{searchRequest}";
//    private static final String SET_BOOK_ENDPOINT = "books";

    private ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public String getBookData(String searchRequest) {

        EndpointUtility endpointUtility = new EndpointUtility();

        BooksResponseFromGoogle booksResponseFromGoogle;

        return endpointUtility.getEndpoint(GET_BOOK_ENDPOINT.replace("{searchRequest}", searchRequest));
    }
//        try { // need to change response into our Book Class model so we can return to the front end
//            booksResponseFromGoogle = mapper.readValue(response, BooksResponseFromGoogle.class);
//            // converts the large response into a set of book records
//            // {
//                // {item 1: key1: val1, key2: val2 },
//                // {item 2: key1: item2val, key2: item2val }
//                // {item 3: key1: item3val, key2: item2val }
//            // return BooksResponseFromGoogle which is a Set<BooksData>
//                // our DTO will use our json IDs to find the matching ones
//                // however, we have a list of books so we need to.map and then find those values
//            // for (nestedObject : BooksResponseFromGoogle) {
//                // create new BooksData from the nestedObject and add to our return value
//
//            BooksResponseConverter converter = new BooksResponseConverter();
//            Set<BooksData> booksResponse = converter.convert(booksResponseFromGoogle);
//
//            Set<BooksData> set = null;
//            for (BooksData book : booksResponseFromGoogle.getBooks()) {
//                set.add(book);
//            }
//
//
//            return booksResponse;
//        } catch (Exception e) {
//            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//    }

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
