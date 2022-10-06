package com.kenzie.capstone.service.utilities;

import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.LambdaBooksRecord;
import com.kenzie.capstone.service.model.BooksResponseFromGoogle;

import java.util.Set;

public class BooksResponseConverter {
    BooksResponseFromGoogle response;
    // convert BooksResponse to BooksRecord

    public Set<BooksData> convert(BooksResponseFromGoogle input) throws Exception {
        // converting books response into set of books record
        if (input.size() > 0) {
            return input.getBooks();
        }
        return null;
    }
}
