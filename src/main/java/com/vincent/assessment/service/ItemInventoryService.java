package com.vincent.assessment.service;

import com.vincent.assessment.model.ItemInventory;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.persistance.entity.ItemInventoryEntity;
import com.vincent.assessment.persistance.repository.ItemInventoryRepository;
import com.vincent.assessment.type.MoneyType;
import com.vincent.assessment.util.VendingMachineMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemInventoryService implements IItemInventoryService {

    private final ItemInventoryRepository repository;

    private final VendingMachineMappers mapper = Mappers.getMapper(VendingMachineMappers.class);

    public ItemInventoryService(final ItemInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addItem(final ItemInventory item) {
        ItemInventoryEntity entityItem = mapper.mapToEntity(item);

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

        ItemInventory item = getItem(purchaseRequest.getItemCode());
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
        ItemInventoryEntity item = repository.findById(itemCode).get();
        log.info("ItemCode: {} - {} - has {} items in the inventory", item.getItemCode(), item.getName(), item.getQuantity());

        return item.getQuantity();
    }

    @Override
    public ItemInventory getItem(final Long itemCode) {
        log.info("Retrieving item in the inventory");
        ItemInventoryEntity entity = repository.findById(itemCode).get();

        return mapper.mapToDTO(entity);
    }

    @Override
    public Iterable<ItemInventory> getAllItems() {

        Iterable<ItemInventoryEntity> items = repository.findAll();

        return mapper.map(items);
    }
}
