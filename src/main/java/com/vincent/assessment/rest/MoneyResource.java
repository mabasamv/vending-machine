package com.vincent.assessment.rest;

import com.vincent.assessment.model.Money;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.service.IMoneyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/money/")
@Tag(name = "Money Resource")
public class MoneyResource {

    private final IMoneyService moneyService;

    public MoneyResource(final IMoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Operation(description = "Returns all the configured denominations")
    @GetMapping("denominations")
    public Iterable<Money> getAmounts() {
        return moneyService.getAll();
    }

    @Operation(description = "Returns the amount for a selected denomination")
    @GetMapping("amount")
    public Money getAmountByDenomination(final MoneyType moneyType) {
        return moneyService.getByDenomination(moneyType);
    }

}
