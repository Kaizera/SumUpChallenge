package com.sumup.codingchallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

import static com.sumup.codingchallenge.Util.createTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JobSorterTest {
    private final AbstractMap.SimpleEntry<String, List<String>> entry1 = new AbstractMap.SimpleEntry<>("task-1",List.of("task-3"));
    private final AbstractMap.SimpleEntry<String,List<String>> entry2 = new AbstractMap.SimpleEntry<>("task-2",List.of("task-1"));
    private final AbstractMap.SimpleEntry<String, List<String>> entry3 = new AbstractMap.SimpleEntry<>("task-3",null);
    private final JobSorter underTest = new JobSorter();


    @Test
    void givenValidList_WhenSorting_isSorted() {
        //given
        List<Task> validList = createTaskList(entry1,entry2,entry3);

        //when
        List<Task> actual = underTest.sort(validList);

        //then
        List<Task> expected = createTaskList(entry3,entry1,entry2);
        assertEquals(expected,actual);
    }
}