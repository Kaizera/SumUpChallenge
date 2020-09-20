package com.sumup.codingchallenge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class JobSorter {

    public List<Task> sort(List<Task> unorderedTasks){
        List<Task> sortedTasks = new ArrayList<>(unorderedTasks.size());
        List<String> sortedTaskNames = new ArrayList<>(unorderedTasks.size());

        Queue<Task> taskQueue = new ArrayBlockingQueue<>(unorderedTasks.size());
        taskQueue.addAll(unorderedTasks);

        while(!taskQueue.isEmpty()) {
            Task topTask = taskQueue.poll();
            if(topTask.getRequires() == null || sortedTaskNames.containsAll(topTask.getRequires())){
                sortedTasks.add(topTask);
                sortedTaskNames.add(topTask.getName());
            } else {
                taskQueue.add(topTask);
            }
        }
        return sortedTasks;
    }
}
