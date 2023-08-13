package com.vincent.assessment.service;

import com.vincent.assessment.model.Inventory;

import java.util.List;

public interface IInventoryService {

    void addItem(final Inventory item);

    void addItems(final List<Inventory> items);

    void removeItem(final Long itemCode);

    Integer getQuantity(final Long itemCode);

    Inventory getItem(final Long itemCode);

    Iterable<Inventory> getAllItems();

}
