package com.vincent.assessment.service;

import com.vincent.assessment.model.ItemInventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.type.MoneyType;

import java.util.List;

public interface IItemInventoryService {

    void addItem(final ItemInventory item);
    void removeItem(final Long itemCode);
    void purchase(final PurchaseRequest request);
    Integer getQuantity(final Long itemCode);

    ItemInventory getItem(final Long itemCode);

    Iterable<ItemInventory> getAllItems();

}
