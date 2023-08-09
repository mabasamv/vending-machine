package com.vincent.assessment.rest;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.service.IChangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/change/")
@Tag(name = "Change Resource")
public class ChangeResource {

    private final IChangeService service;

    public ChangeResource(final IChangeService service) {
        this.service = service;
    }

    @Operation(description = "Returns all the configured denominations")
    @GetMapping("denominations")
    public Iterable<Change> getAmounts() {
        return service.getAll();
    }

    @Operation(description = "Returns the amount for a selected denomination")
    @GetMapping("amount")
    public Change getAmountByDenomination(final MoneyType moneyType) {
        return service.getByDenomination(moneyType);
    }

    @Operation(description = "Loads change to the vending machine")
    @PostMapping("load")
    public void loadChange(final @RequestBody Change change) {
        service.loadChange(change);
    }
}
