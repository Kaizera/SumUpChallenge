package com.sumup.codingchallenge;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sumup.codingchallenge.JsonValidator.MISSINGREQUIREDTASKMESSAGE;
import static com.sumup.codingchallenge.JsonValidator.NULLTASKSMESSAGE;
import static com.sumup.codingchallenge.Util.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonValidatorTest {
    private final JsonValidator underTest = new JsonValidator();

    @Test
    void givenValidTaskList_WhenValidating_IsValid() {
        //given
        List<Task> validList = createTaskList(ENTRY3,ENTRY1);

        //when
        ValidationResult validationResult = underTest.isValidJson(validList);

        //then
        assertTrue(validationResult.isValid());
    }

    @Test
    void givenInvalidTaskList_WhenValidating_IsNotValid() {
        //given
        List<Task> validList = createTaskList(ENTRY3,ENTRY2);

        //when
        ValidationResult validationResult = underTest.isValidJson(validList);

        //then
        assertFalse(validationResult.isValid());
        assertEquals(MISSINGREQUIREDTASKMESSAGE, validationResult.getMessage());
    }

    @Test
    void givenNullTaskList_WhenValidating_IsNotValid() {
        //given
        List<Task> validList = null;

        //when
        ValidationResult validationResult = underTest.isValidJson(validList);

        //then
        assertFalse(validationResult.isValid());
        assertEquals(NULLTASKSMESSAGE, validationResult.getMessage());
    }

}