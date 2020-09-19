package com.sumup.codingchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobProcessingResource {

    @Autowired
    private JobSorter sorter;

    @Autowired
    private JsonValidator validator;

    @PostMapping("/sort-task")
    public ResponseEntity<Object> jobProcessor(@RequestBody Map<String, List<Task>> payload) throws JsonProcessingException {
        //parse and validate
        List<Task> tasks = payload.get("tasks");

        if(!validator.isValidJson(tasks)){
            Map<String, Object> body = new HashMap<>();
            body.put("message", "A required task is not present in the list of tasks to be performed");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        //sort
        ArrayList<Task> sortedTasks = sorter.sort(tasks);


        return new ResponseEntity<>(sortedTasks,HttpStatus.OK);
    }
}