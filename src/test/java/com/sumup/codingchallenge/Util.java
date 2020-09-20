package com.sumup.codingchallenge;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class Util {

    static final SimpleEntry<String, List<String>> ENTRY1 = new SimpleEntry<>("task-1",List.of("task-3"));
    static final SimpleEntry<String,List<String>> ENTRY2 = new SimpleEntry<>("task-2",List.of("task-1"));
    static final SimpleEntry<String, List<String>> ENTRY3 = new SimpleEntry<>("task-3",null);

    @SafeVarargs
    public static List<Task> createTaskList(SimpleEntry<String,List<String>>... pairs){
        List<Task> tasks = new ArrayList<>();
        for(SimpleEntry<String, List<String>> pair : pairs){
            tasks.add(createTask(pair));
        }
        return tasks;
    }

    public static Task createTask(SimpleEntry<String,List<String>> pair){
        return new Task(pair.getKey(), "", pair.getValue());
    }
}
