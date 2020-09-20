package com.sumup.codingchallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static com.sumup.codingchallenge.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class JobProcessingResourceTest {

    @Mock
    private JsonValidator jsonValidatorMock;

    @Mock
    private JobSorter jobSorterMock;

    @InjectMocks
    private JobProcessingResource underTest;

    private final List<Task> tasks = createTaskList(ENTRY1,ENTRY2,ENTRY3);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(jobSorterMock.sort(any())).thenReturn(tasks);
    }

    @Test
    void givenListIsValidAndListSorted_WhenJobProcessed_ThenReturnOkAndCorrectBody() {
        //given
        when(jsonValidatorMock.isValidJson(any())).thenReturn(true);

        //when
        ResponseEntity<Object> response = underTest.jobProcessor(Map.of("tasks", tasks));

        //then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), tasks);
    }

    @Test
    void givenListIsNotValidAndListSorted_WhenJobProcessed_ThenReturnBadRequest() {
        //given
        when(jsonValidatorMock.isValidJson(any())).thenReturn(false);

        //when
        ResponseEntity<Object> response = underTest.jobProcessor(Map.of("tasks", tasks));

        //then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}