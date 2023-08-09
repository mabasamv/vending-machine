package com.vincent.assessment.exception;

import lombok.Getter;

@Getter
public class NoSufficientChangeException extends RuntimeException {
    private final String message;

    public NoSufficientChangeException(final String string) {
        this.message = string;
    }
}
