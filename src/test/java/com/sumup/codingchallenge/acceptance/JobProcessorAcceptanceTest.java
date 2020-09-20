package com.sumup.codingchallenge.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobProcessorAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenRunningApplication_WhenRequestReceived_ThenReturnExpectedJsonOutput() throws Exception {
        String input = readFileAsString("src/test/resources/input.json");
        String expected = readFileAsString("src/test/resources/expected.json");

        mockMvc.perform(post("/sort-task/json")
                .contentType("application/json")
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }

    @Test
    void givenRunningApplication_WhenRequestReceived_ThenReturnExpectedTextOutput() throws Exception {
        String input = readFileAsString("src/test/resources/input.json");
        String expectedString = readFileAsString("src/test/resources/expected-bash");

        mockMvc.perform(post("/sort-task/bash")
                .contentType("application/json")
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));

    }

    private String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}