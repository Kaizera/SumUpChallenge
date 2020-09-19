package com.sumup.codingchallenge;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
public class Task {
    @NonNull
    private String name;
    @NonNull
    private String command;
    private List<String> requires;
}