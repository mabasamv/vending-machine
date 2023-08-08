package com.vincent.assessment.rest;

import com.vincent.assessment.service.IVendingMachineService;
import com.vincent.assessment.type.ItemType;
import com.vincent.assessment.type.MoneyType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/")
@Tag(name = "Vending Machine Resource", description = "APIs to simulate a Vending Machine")
public class VendingMachineResource {

    private final IVendingMachineService vendingMachine;

    public VendingMachineResource(final IVendingMachineService vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @GetMapping("check-price")
    public long checkPrice(@PathParam("item") ItemType item) {
        long price = vendingMachine.selectItemAndGetPrice(item);
        log.info("Price of {} is R{}", item.getName(), price);
        return price;
    }

    @PostMapping("insert-money")
    public void insertMoney(@RequestBody MoneyType money) {
        vendingMachine.insertMoney(money);
    }

    @Operation(description = "The API to reset the vending machine")
    @DeleteMapping("reset")
    public void reset() {
        vendingMachine.reset();
    }

}
