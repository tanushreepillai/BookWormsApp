package com.kenzie.capstone.service.dao;

import com.kenzie.capstone.service.model.BooksRecord;

import java.util.List;

public interface BaseBooksDao {

    public List<BooksRecord> getBooksData(String id);

}
