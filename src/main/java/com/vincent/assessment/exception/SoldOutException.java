package com.vincent.assessment.exception;

import lombok.Getter;

@Getter
public class SoldOutException extends RuntimeException {
    private final String message;

    public SoldOutException(final String string) {
        this.message = string;
    }
}
