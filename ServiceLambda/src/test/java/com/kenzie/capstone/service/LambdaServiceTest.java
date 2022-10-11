package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.LambdaBooksDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LambdaServiceTest {

    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseById
     *  ------------------------------------------------------------------------ **/

    private LambdaService lambdaService;
    private LambdaBooksDao lambdaBooksDao;

    @BeforeAll
    void setup() {
        this.lambdaService = new LambdaService(lambdaBooksDao);
    }

    @Test
    void setDataTest() {
//        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<String> dataCaptor = ArgumentCaptor.forClass(String.class);
//œ
//        // GIVEN
//        String data = "somedata";
//
//        // WHEN
//        ExampleData response = this.lambdaService.setExampleData(data);
//
//        // THEN
//        verify(exampleDao, times(1)).setExampleData(idCaptor.capture(), dataCaptor.capture());
//
//        assertNotNull(idCaptor.getValue(), "An ID is generated");
//        assertEquals(data, dataCaptor.getValue(), "The data is saved");
//
//        assertNotNull(response, "A response is returned");
//        assertEquals(idCaptor.getValue(), response.getId(), "The response id should match");
//        assertEquals(data, response.getData(), "The response data should match");
    }

    @Test
    void getDataTest() throws IOException, InterruptedException {
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);

        // GIVEN
        String searchRequest = "foundationasimov";
//        ExampleRecord record = new ExampleRecord();
//        record.setId(id);
//        record.setData(data);
//
//
//        when(exampleDao.getExampleData(id)).thenReturn(Arrays.asList(record));

        // WHEN
        HttpResponse<String> response = this.lambdaService.getBookData(searchRequest);
        System.out.println("response: " + response);

        // THEN
//        verify(exampleDao, times(1)).getExampleData(idCaptor.capture());
//
//        assertEquals(id, idCaptor.getValue(), "The correct id is used");
//
        assertNotNull(response, "A response is returned");
//        assertTrue(response.getId(), "The response id should match");
//        assertEquals(data, response.getData(), "The response data should match");
    }

    // Write additional tests here

}