package com.vincent.assessment.service;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import com.vincent.assessment.persistance.repository.InventoryRepository;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.util.InventoryMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository repository;

    private final InventoryMappers mapper = Mappers.getMapper(InventoryMappers.class);

    public InventoryService(final InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addItem(final Inventory item) {
        InventoryEntity entityItem = mapper.map(item);

        log.info("Saving item to the inventory");

        repository.save(entityItem);
    }

    @Override
    public void removeItem(final Long itemCode) {
        log.info("Removing item from the inventory");
        repository.deleteById(itemCode);
    }

    @Override
    public void purchase(final PurchaseRequest purchaseRequest) {
        log.info("Purchasing item");

        Inventory item = getItem(purchaseRequest.getItemCode());
        log.info("Purchasing item {}", item);

        List<MoneyType> denominations = purchaseRequest.getDenominations();
        int totalAmount = 0;
        for (MoneyType mt: denominations) {
            totalAmount = totalAmount + mt.getAmount();
        }

        log.info("Total Amount: {}", totalAmount);

        if(totalAmount >= item.getUnitPrice() && item.getQuantity() >0) {
            log.info("Proceed with payment and give change");
        } else {
            log.info("Transaction failed!");
        }
    }

    @Override
    public Integer getQuantity(final Long itemCode) {
        InventoryEntity item = repository.findById(itemCode).get();
        log.info("ItemCode: {} - {} - has {} items in the inventory", item.getItemCode(), item.getName(), item.getQuantity());

        return item.getQuantity();
    }

    @Override
    public Inventory getItem(final Long itemCode) {
        log.info("Retrieving item in the inventory");
        InventoryEntity entity = repository.findById(itemCode).get();

        return mapper.map(entity);
    }

    @Override
    public Iterable<Inventory> getAllItems() {

        Iterable<InventoryEntity> items = repository.findAll();

        return mapper.map(items);
    }
}
