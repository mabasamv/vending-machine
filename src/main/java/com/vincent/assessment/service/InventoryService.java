package com.vincent.assessment.service;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NotSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import com.vincent.assessment.persistance.repository.InventoryRepository;
import com.vincent.assessment.util.InventoryMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vincent.assessment.util.VendingMachineUtil.*;

@Slf4j
@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository repository;

    private final IChangeService changeService;
    private final InventoryMappers mapper = Mappers.getMapper(InventoryMappers.class);

    public InventoryService(final InventoryRepository repository, final IChangeService changeService) {
        this.repository = repository;
        this.changeService = changeService;
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

        int quantity = getQuantity(purchaseRequest.getItemCode());
        log.info("Quantity: {}", quantity);

        if (quantity > 0) {
            List<MoneyType> amount = purchaseRequest.getDenominations();
            int totalAmount = totalAmount(amount);
            log.info("Total Amount: {}", totalAmount);

            if (totalAmount >= item.getUnitPrice()) {
                log.info("Process and give change");

                Integer totalChange = totalChange(changeService);
                log.info("Total change: {}", totalChange);

                if (totalAmount > totalChange)
                    throw new NotSufficientChangeException("No sufficient change in vending machine, transaction will be cancelled");
                else
                    processPurchase(item, totalAmount);
            } else {
                long remaining = item.getUnitPrice() - totalAmount;
                throw new NotFullPaidException("Insufficient amount provided for purchase", remaining);
            }
        } else {
            throw new SoldOutException("Items sold out");
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

    private void processPurchase(final Inventory item, final int totalAmount) {
        log.info("Deduct quantity from product");
        deductQuantity(item);

        int change = totalAmount - item.getUnitPrice();
        log.info("Change: R{}", change);

        deductChange(changeService, change);
        log.info("Transaction successful");
    }

    private void deductQuantity(final Inventory item) {
        Integer deduct = item.getQuantity() - 1;
        item.setQuantity(deduct);

        addItem(item);
    }
}
