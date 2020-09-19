package com.sumup.codingchallenge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Component
public class JobSorter {

    public List<Task> sort(List<Task> unorderedTasks){
        //sort
        ArrayList<Task> sortedTasks = new ArrayList<>(unorderedTasks.size());
        ArrayList<String> sortedTaskNames = new ArrayList<>(unorderedTasks.size());

        //put in queue
        Queue<Task> taskQueue = new ArrayBlockingQueue<>(unorderedTasks.size());
        taskQueue.addAll(unorderedTasks);

        while(!taskQueue.isEmpty()) {
            Task taskIterator = taskQueue.poll();
            if(taskIterator.getRequires() == null || sortedTaskNames.containsAll(taskIterator.getRequires())){
                sortedTasks.add(taskIterator);
                sortedTaskNames.add(taskIterator.getName());
            } else {
                taskQueue.add(taskIterator);
            }
        }
        return sortedTasks;
    }
}
