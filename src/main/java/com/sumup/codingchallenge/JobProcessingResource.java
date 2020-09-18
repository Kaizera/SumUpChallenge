package com.sumup.codingchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class JobProcessingResource {

    @Autowired
    private JobSorter sorter;

    @PostMapping("/sort-task")
    public String jobProcessor(@RequestBody Map<String, ArrayList<Task>> payload) throws JsonProcessingException {
        //parse and validate
        System.out.println(payload);

        //sort
        ArrayList<Task> sortedTasks = sorter.sort(payload.get("tasks"));


        return payload.toString();
    }
}