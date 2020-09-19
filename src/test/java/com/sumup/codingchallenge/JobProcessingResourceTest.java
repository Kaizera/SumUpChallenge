package com.sumup.codingchallenge;

import com.sumup.codingchallenge.JobProcessingResource;
import com.sumup.codingchallenge.JobSorter;
import com.sumup.codingchallenge.JsonValidator;
import com.sumup.codingchallenge.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sumup.codingchallenge.Util.createTaskList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class JobProcessingResourceTest {

    @Mock
    private JsonValidator jsonValidatorMock;

    @Mock
    private JobSorter jobSorterMock;

    @InjectMocks
    @Resource
    private JobProcessingResource underTest;

    private final AbstractMap.SimpleEntry<String, List<String>> entry1 = new AbstractMap.SimpleEntry<>("task-1",List.of("task-3"));
    private final AbstractMap.SimpleEntry<String,List<String>> entry2 = new AbstractMap.SimpleEntry<>("task-2",List.of("task-1"));
    private final AbstractMap.SimpleEntry<String, List<String>> entry3 = new AbstractMap.SimpleEntry<>("task-3",null);
    private final List<Task> tasks = createTaskList(entry1,entry2,entry3);



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(jobSorterMock.sort(any())).thenReturn(tasks);

    }

    @Test
    void givenListIsValidAndListSorted_ThenReturnOkAndCorrectBody() {
        //given
        when(jsonValidatorMock.isValidJson(any())).thenReturn(true);

        ResponseEntity<Object> response = underTest.jobProcessor(Map.of("tasks", tasks));

        //then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), tasks);
    }

    @Test
    void givenListIsNotValidAndListSorted_ThenReturnBadRequest() {
        when(jsonValidatorMock.isValidJson(any())).thenReturn(false);

        ResponseEntity<Object> response = underTest.jobProcessor(Map.of("tasks", tasks));

        //then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}