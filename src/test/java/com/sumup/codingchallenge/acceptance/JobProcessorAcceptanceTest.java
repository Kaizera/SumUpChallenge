package com.sumup.codingchallenge.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobProcessorAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    private final String inputJson = "{\"tasks\":[{\"name\":\"task-1\",\"command\":\"touch /tmp/file1\"},{\"name\":\"task-2\",\"command\":\"cat /tmp/file1\",\"requires\":[\"task-3\"]},{\"name\":\"task-3\",\"command\":\"echo 'Hello World!' > /tmp/file1\",\"requires\":[\"task-1\"]},{\"name\":\"task-4\",\"command\":\"rm /tmp/file1\",\"requires\":[\"task-2\",\"task-3\"]}]}";

    @Test
    void givenRunningApplication_WhenRequestReceived_ThenReturnExpectedJsonOutput() throws Exception {
        String expectedJson = "[{\"name\":\"task-1\",\"command\":\"touch /tmp/file1\",\"requires\":null},{\"name\":\"task-3\",\"command\":\"echo 'Hello World!' > /tmp/file1\",\"requires\":[\"task-1\"]},{\"name\":\"task-2\",\"command\":\"cat /tmp/file1\",\"requires\":[\"task-3\"]},{\"name\":\"task-4\",\"command\":\"rm /tmp/file1\",\"requires\":[\"task-2\",\"task-3\"]}]";


        mockMvc.perform(post("/sort-task/json")
                .contentType("application/json")
                .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    void givenRunningApplication_WhenRequestReceived_ThenReturnExpectedTextOutput() throws Exception {
        String expectedString = "touch /tmp/file1\n" +
                "echo 'Hello World!' > /tmp/file1\n" +
                "cat /tmp/file1\n" +
                "rm /tmp/file1\n";


        mockMvc.perform(post("/sort-task/bash")
                .contentType("application/json")
                .content(inputJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));

    }
}