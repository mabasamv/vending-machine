package com.vincent.assessment.service;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.PurchaseRequest;

public interface IInventoryService {

    void addItem(final Inventory item);
    void removeItem(final Long itemCode);
    void purchase(final PurchaseRequest request);
    Integer getQuantity(final Long itemCode);

    Inventory getItem(final Long itemCode);

    Iterable<Inventory> getAllItems();

}
