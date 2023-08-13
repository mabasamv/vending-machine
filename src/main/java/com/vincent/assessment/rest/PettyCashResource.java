package com.vincent.assessment.rest;

import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.service.IPettyCashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/petty-cash/")
@Tag(name = "Petty Cash Resource")
public class PettyCashResource {

    private final IPettyCashService service;

    public PettyCashResource(final IPettyCashService service) {
        this.service = service;
    }

    @Operation(description = "Returns all the configured denominations")
    @GetMapping("denominations")
    public Iterable<PettyCash> getAmounts() {
        return service.getAll();
    }

    @Operation(description = "Returns the amount for a selected denomination")
    @GetMapping("amount")
    public PettyCash getAmountByDenomination(final MoneyType moneyType) {
        return service.getByDenomination(moneyType);
    }

    @Operation(description = "Loads change to the vending machine")
    @PostMapping("load")
    public void loadCash(final @RequestBody PettyCash pettyCash) {
        service.loadCash(pettyCash);
    }

    @Operation(description = "Gets all change in the vending machine")
    @GetMapping("all-change")
    public Iterable<PettyCash> getAll() {
        return service.getAll();
    }
}
