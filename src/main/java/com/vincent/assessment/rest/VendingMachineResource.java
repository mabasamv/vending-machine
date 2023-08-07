package com.vincent.assessment.rest;

import com.vincent.assessment.service.IVendingMachine;
import com.vincent.assessment.type.Item;
import com.vincent.assessment.type.Money;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/")
@Tag(name = "Vending Machine", description = "APIs to simulate a Vending Machine")
public class VendingMachineResource {

    private final IVendingMachine vendingMachine;

    public VendingMachineResource(IVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @GetMapping("check-price")
    public long checkPrice(@PathParam("item") Item item) {
        log.info("sdssdd");
        long price = vendingMachine.selectItemAndGetPrice(item);
        log.info("Price of {} is R{}", price, item.getName());
        return price;
    }

    @PostMapping("insert-money")
    public void insertMoney(@RequestBody Money money) {
        vendingMachine.insertMoney(money);
    }

    @Operation(summary = "Reset Vending Machine",
            description = "The API to reset the vending machine")
    @DeleteMapping("reset")
    public void reset() {
        vendingMachine.reset();
    }

}
