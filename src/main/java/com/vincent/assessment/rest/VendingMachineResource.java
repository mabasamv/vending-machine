package com.vincent.assessment.rest;

import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.model.PurchaseResponse;
import com.vincent.assessment.service.IVendingMachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@Tag(name = "Vending Machine Resource")
public class VendingMachineResource {

    private final IVendingMachineService service;

    public VendingMachineResource(final IVendingMachineService service) {
        this.service = service;
    }

    @Operation(description = "Purchase item and deduct quantity from the inventory")
    @PostMapping("purchase")
    public PurchaseResponse purchase(@RequestBody PurchaseRequest request) {
        return service.purchase(request);
    }
}
