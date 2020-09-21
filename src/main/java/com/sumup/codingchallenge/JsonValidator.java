package com.sumup.codingchallenge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonValidator {
    public static final String NULLTASKSMESSAGE = "The request should contain a list of 'tasks'";
    public static final String MISSINGREQUIREDTASKMESSAGE = "A required task is not present in the list of tasks to be performed";

    public ValidationResult isValidJson(List<Task> tasks){

        if(tasks == null){
            return new ValidationResult(NULLTASKSMESSAGE, false);
        }

        List<String> taskNames = new ArrayList<>();
        List<String> requireList = new ArrayList<>();

        for(Task task : tasks){
            taskNames.add(task.getName());
            if(task.getRequires() != null){
                requireList.addAll(task.getRequires());
            }
        }

        boolean isValid = taskNames.containsAll(requireList);
        return new ValidationResult(MISSINGREQUIREDTASKMESSAGE, isValid);
    }
}
