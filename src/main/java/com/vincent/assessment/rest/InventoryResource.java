package com.vincent.assessment.rest;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.service.IInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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

    @Operation(description = "Adds multiple items in the inventory")
    @PostMapping("add-multiple-item")
    public void addItems(@RequestBody List<Inventory> items) {
        service.addItems(items);
    }

    @Operation(description = "Removes an item in the inventory")
    @DeleteMapping("remove-item")
    public void removeItem(@PathParam("itemCode") Long itemCode) {
        service.removeItem(itemCode);
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
