package com.vincent.assessment.exception;

import lombok.Getter;

@Getter
public class VendingMachineException extends RuntimeException {
    private final String message;

    public VendingMachineException(final String string) {
        this.message = string;
    }
}
