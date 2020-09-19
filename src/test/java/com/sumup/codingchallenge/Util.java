package com.sumup.codingchallenge;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class Util {
    @SafeVarargs
    public static List<Task> createTaskList(AbstractMap.SimpleEntry<String,List<String>>... pairs){
        List<Task> tasks = new ArrayList<>();
        for(AbstractMap.SimpleEntry<String, List<String>> pair : pairs){
            tasks.add(createTask(pair));
        }
        return tasks;
    }

    public static Task createTask(AbstractMap.SimpleEntry<String,List<String>> pair){
        return new Task(pair.getKey(), "", pair.getValue());
    }
}
