package com.vincent.assessment.advice;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NoSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.exception.VendingMachineException;
import com.vincent.assessment.model.ErrorResponse;
import com.vincent.assessment.rest.InventoryResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

import static com.vincent.assessment.util.VendingMachineUtil.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice(assignableTypes = InventoryResource.class)
public class InventoryResourceAdvice {
    @ExceptionHandler(VendingMachineException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleVendingMachineExceptions(final Exception e) {
        final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        log.error(message, e);
        return getErrorResponse(message, INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(NotFullPaidException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleNotFullPaidExceptions(final Exception e) {
        final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        log.error(message, e);
        return getErrorResponse(message, NOT_FULLY_PAID);
    }

    @ExceptionHandler(NoSufficientChangeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleNoSufficientChangeExceptions(final Exception e) {
        final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        log.error(message, e);
        return getErrorResponse(message, NO_SUFFICIENT_CHANGE);
    }

    @ExceptionHandler(SoldOutException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleSoldOutExceptions(final Exception e) {
        final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        log.error(message, e);
        return getErrorResponse(message, SOLD_OUT);
    }
}
