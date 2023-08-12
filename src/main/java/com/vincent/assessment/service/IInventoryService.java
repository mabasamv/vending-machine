package com.vincent.assessment.service;

import com.vincent.assessment.model.Inventory;

public interface IInventoryService {

    void addItem(final Inventory item);

    void removeItem(final Long itemCode);

    Integer getQuantity(final Long itemCode);

    Inventory getItem(final Long itemCode);

    Iterable<Inventory> getAllItems();

}
