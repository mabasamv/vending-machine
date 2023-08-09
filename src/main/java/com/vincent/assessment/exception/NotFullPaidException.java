package com.vincent.assessment.exception;

import lombok.Getter;

@Getter
public class NotFullPaidException extends RuntimeException {
    private final String message;

    public NotFullPaidException(final String message) {
        this.message = message;
    }
}