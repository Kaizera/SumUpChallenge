package com.sumup.codingchallenge;


import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import static com.sumup.codingchallenge.Util.createTaskList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonValidatorTest {
    private final SimpleEntry<String,List<String>> entry1 = new SimpleEntry<>("task-1",null);;
    private final SimpleEntry<String,List<String>> entry2 = new SimpleEntry<>("task-2",List.of("task-1"));
    private final SimpleEntry<String, List<String>> entry3 = new SimpleEntry<>("task-3",List.of("task-4"));
    private final JsonValidator underTest = new JsonValidator();



    @Test
    void givenValidTaskList_WhenValidating_IsValid() {
        //given
        List<Task> validList = createTaskList(entry1,entry2);

        //when
        boolean check = underTest.isValidJson(validList);

        //then
        assertTrue(check);
    }

    @Test
    void givenInvalidTaskList_WhenValidating_IsNotValid() {
        //given
        List<Task> validList = createTaskList(entry1,entry3);

        //when
        boolean check = underTest.isValidJson(validList);

        //then
        assertFalse(check);
    }

}