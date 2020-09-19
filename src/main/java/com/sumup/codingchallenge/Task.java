package com.sumup.codingchallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    @NonNull
    private String name;
    @NonNull
    private String command;
    private List<String> requires;
}