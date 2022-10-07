package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.BooksRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface BookRepository extends CrudRepository<BooksRecord, String> {
}
