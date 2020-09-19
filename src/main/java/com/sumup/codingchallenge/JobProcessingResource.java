package com.sumup.codingchallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sort-task")
public class JobProcessingResource {

    @Autowired
    private JobSorter sorter;

    @Autowired
    private JsonValidator validator;

    @PostMapping("/json")
    public ResponseEntity<Object> jobProcessor(@RequestBody Map<String, List<Task>> payload) {
        //parse and validate
        System.out.println(payload);
        List<Task> tasks = payload.get("tasks");

        if(!validator.isValidJson(tasks)){
            Map<String, Object> body = new HashMap<>();
            body.put("message", "A required task is not present in the list of tasks to be performed");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        //sort
        List<Task> sortedTasks = sorter.sort(tasks);


        return new ResponseEntity<>(sortedTasks,HttpStatus.OK);
    }

    @PostMapping("/bash")
    public ResponseEntity<Object> jobProcessorBash(@RequestBody Map<String, List<Task>> payload) {
        //parse and validate
        List<Task> tasks = payload.get("tasks");

        if (!validator.isValidJson(tasks)) {
            Map<String, Object> body = new HashMap<>();
            body.put("message", "A required task is not present in the list of tasks to be performed");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        //sort
        List<Task> sortedTasks = sorter.sort(tasks);

        StringBuilder result = new StringBuilder();
        for (Task task : sortedTasks) {
            result.append(task.getCommand());
            result.append("\n");
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}