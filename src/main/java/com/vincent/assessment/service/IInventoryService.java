package com.vincent.assessment.service;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NoSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.model.PurchaseResponse;

public interface IInventoryService {

    void addItem(final Inventory item);
    void removeItem(final Long itemCode);
    PurchaseResponse purchase(final PurchaseRequest request) throws NotFullPaidException, NoSufficientChangeException, SoldOutException, Exception;
    Integer getQuantity(final Long itemCode);

    Inventory getItem(final Long itemCode);

    Iterable<Inventory> getAllItems();

}
