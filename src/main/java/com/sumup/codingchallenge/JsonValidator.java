package com.sumup.codingchallenge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonValidator {

    public boolean isValidJson(List<Task> tasks){
        List<String> taskNames = new ArrayList<>();
        List<String> requireList = new ArrayList<>();

        for(Task task : tasks){
            taskNames.add(task.getName());
            if(task.getRequires() != null){
                requireList.addAll(task.getRequires());
            }
        }

        return taskNames.containsAll(requireList);
    }
}
