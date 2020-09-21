package com.sumup.codingchallenge;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationResult {
    private String message;
    private boolean isValid;
}
