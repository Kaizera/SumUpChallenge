package com.sumup.codingchallenge;


import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sumup.codingchallenge.Util.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonValidatorTest {
    private final JsonValidator underTest = new JsonValidator();

    @Test
    void givenValidTaskList_WhenValidating_IsValid() {
        //given
        List<Task> validList = createTaskList(ENTRY3,ENTRY1);

        //when
        boolean check = underTest.isValidJson(validList);

        //then
        assertTrue(check);
    }

    @Test
    void givenInvalidTaskList_WhenValidating_IsNotValid() {
        //given
        List<Task> validList = createTaskList(ENTRY3,ENTRY2);

        //when
        boolean check = underTest.isValidJson(validList);

        //then
        assertFalse(check);
    }

}