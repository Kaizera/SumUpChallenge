package com.sumup.codingchallenge;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Task {
    private String name;
    private String command;
    private List<String> requires;
}