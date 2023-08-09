package com.vincent.assessment.rest;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.model.PurchaseResponse;
import com.vincent.assessment.service.IInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/inventory/")
@Tag(name = "Inventory Resource")
public class InventoryResource {

    private final IInventoryService service;

    public InventoryResource(final IInventoryService inventoryService) {
        this.service = inventoryService;
    }

    @Operation(description = "Creates an item in the inventory")
    @PostMapping("add-item")
    public void addItem(@RequestBody Inventory item) {
        service.addItem(item);
    }

    @Operation(description = "Removes an item in the inventory")
    @DeleteMapping("remove-item")
    public void removeItem(@PathParam("itemCode") Long itemCode) {
        service.removeItem(itemCode);
    }

    @Operation(description = "Deduct quantity from an item in the inventory")
    @PostMapping("purchase-item")
    public PurchaseResponse purchase(@RequestBody PurchaseRequest request) {
        return service.purchase(request);
    }

    @Operation(description = "Get item quantity in the inventory")
    @GetMapping("get-quantity")
    public Integer getQuantity(@PathParam("itemCode") Long itemCode) {
        return service.getQuantity(itemCode);
    }

    @Operation(description = "Gets item in the inventory")
    @GetMapping("get-item")
    public Inventory getItem(@PathParam("itemCode") Long itemCode) {
        return service.getItem(itemCode);
    }

    @Operation(description = "Gets all items in the inventory")
    @GetMapping("get-all-items")
    public Iterable<Inventory> getItems() {
        return service.getAllItems();
    }

}
