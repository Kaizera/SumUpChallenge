package com.sumup.codingchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Queue;
        import java.util.concurrent.ArrayBlockingQueue;
        import java.util.concurrent.BlockingDeque;

@RestController
public class JobProcessingResource {

    @PostMapping("/sort-task")
    public static String jobProcessor(@RequestBody Tasks payload) throws JsonProcessingException {
        //parse and validate
        System.out.println(payload);

        return payload.toString();
    }
}