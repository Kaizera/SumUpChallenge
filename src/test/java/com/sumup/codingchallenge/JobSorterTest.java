package com.sumup.codingchallenge;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sumup.codingchallenge.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JobSorterTest {
    private final JobSorter underTest = new JobSorter();

    @Test
    void givenValidList_WhenSorting_isSorted() {
        //given
        List<Task> validList = createTaskList(ENTRY1,ENTRY2,ENTRY3);

        //when
        List<Task> actual = underTest.sort(validList);

        //then
        List<Task> expected = createTaskList(ENTRY3,ENTRY1,ENTRY2);
        assertEquals(expected,actual);
    }
}