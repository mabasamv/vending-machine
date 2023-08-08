package com.vincent.assessment.rest;

import com.vincent.assessment.persistance.entity.MoneyEntity;
import com.vincent.assessment.service.IMoneyService;
import com.vincent.assessment.type.MoneyType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/money/")
@Tag(name = "Money Resource", description = "APIs to view pre-configured amounts and their denominations")
public class MoneyResource {

    private final IMoneyService moneyService;

    public MoneyResource(final IMoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Operation(description = "Returns the amount for a selected denomination")
    @GetMapping("amount")
    public Integer getAmountByDenomination(final MoneyType moneyType) {
        return moneyService.getAmountByDenomination(moneyType);
    }

    @Operation(description = "Returns all the configured amounts")
    @GetMapping("amounts")
    public Iterable<MoneyEntity> getAmounts() {
        return moneyService.getAmounts();
    }
}
