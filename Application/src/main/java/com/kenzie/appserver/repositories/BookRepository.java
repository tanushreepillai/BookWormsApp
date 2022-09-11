package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.BookRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface BookRepository extends CrudRepository<BookRecord, String> {
}
