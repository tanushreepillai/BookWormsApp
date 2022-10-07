package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.BookRepository;

import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    private BookRepository exampleRepository;
    private LambdaServiceClient lambdaServiceClient;

    public ExampleService(BookRepository exampleRepository, LambdaServiceClient lambdaServiceClient) {
        this.exampleRepository = exampleRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

//    public Example findById(String id) {
//
//        // Example getting data from the lambda
//        ExampleData dataFromLambda = lambdaServiceClient.getExampleData(id);
//
//        // Example getting data from the local repository
//        Example dataFromDynamo = exampleRepository
//                .findById(id)
//                .map(example -> new Example(example.getId(), example.getName()))
//                .orElse(null);
//
//        return dataFromDynamo;
//    }

//    public Example addNewExample(String name) {
//        // Example sending data to the lambda
//        ExampleData dataFromLambda = lambdaServiceClient.setExampleData(name);
//
//        // Example sending data to the local repository
//        BookRecord exampleRecord = new BookRecord();
//        exampleRecord.setId(dataFromLambda.getId());
//        exampleRecord.setName(dataFromLambda.getData());
//        exampleRepository.save(exampleRecord);
//
//        Example example = new Example(dataFromLambda.getId(), name);
//        return example;
//    }
}
