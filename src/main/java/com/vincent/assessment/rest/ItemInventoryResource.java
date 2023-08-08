package com.vincent.assessment.rest;

import com.vincent.assessment.model.ItemInventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.persistance.entity.ItemInventoryEntity;
import com.vincent.assessment.service.IItemInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/inventory/")
@Tag(name = "Item Inventory Resource")
public class ItemInventoryResource {

    private final IItemInventoryService inventoryService;

    public ItemInventoryResource(final IItemInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Operation(description = "Creates an item in the inventory")
    @PostMapping("add-item")
    public void addItem(@RequestBody ItemInventory item) {
        inventoryService.addItem(item);
    }

    @Operation(description = "Removes an item in the inventory")
    @DeleteMapping("remove-item")
    public void removeItem(@PathParam("itemCode") Long itemCode) {
        inventoryService.removeItem(itemCode);
    }

    @Operation(description = "Deduct quantity from an item in the inventory")
    @PutMapping("purchase-item")
    public void deduct(@RequestBody PurchaseRequest request) {
        inventoryService.purchase(request);
    }

    @Operation(description = "Get item quantity in the inventory")
    @GetMapping("get-quantity")
    public Integer getQuantity(@PathParam("itemCode") Long itemCode) {
        return inventoryService.getQuantity(itemCode);
    }

    @Operation(description = "Gets item in the inventory")
    @GetMapping("get-item")
    public ItemInventory getItem(@PathParam("itemCode") Long itemCode) {
        return inventoryService.getItem(itemCode);
    }

    @Operation(description = "Gets all items in the inventory")
    @GetMapping("get-all-items")
    public Iterable<ItemInventory> getItems() {
        return inventoryService.getAllItems();
    }

}
