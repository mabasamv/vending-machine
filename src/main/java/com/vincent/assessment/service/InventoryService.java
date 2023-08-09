package com.vincent.assessment.service;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NotSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.model.PurchaseResponse;
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
        log.info("Adding/updating item to the inventory");
        InventoryEntity entityItem = mapper.map(item);

        repository.save(entityItem);
    }

    @Override
    public void removeItem(final Long itemCode) {
        log.info("Removing item from the inventory");
        repository.deleteById(itemCode);
    }

    @Override
    public PurchaseResponse purchase(final PurchaseRequest purchaseRequest) {
        log.info("Purchase item");
        Inventory item = getItem(purchaseRequest.getItemCode());
        int quantity = getQuantity(purchaseRequest.getItemCode());

        if (quantity > 0) {
            List<MoneyType> amount = purchaseRequest.getDenominations();

            int totalAmount = totalAmount(amount);
            if (totalAmount >= item.getUnitPrice()) {
                if (totalAmount > totalChange(changeService))
                    throw new NotSufficientChangeException("No sufficient change in vending machine, transaction will be cancelled");
                else
                    return processPurchase(item, totalAmount);
            } else
                throw new NotFullPaidException("Insufficient amount provided for purchase", item.getUnitPrice() - totalAmount);
        } else
            throw new SoldOutException("Item sold out");
    }

    @Override
    public Integer getQuantity(final Long itemCode) {
        InventoryEntity item = repository.findById(itemCode).orElse(null);
        assert item != null;

        log.info("{} has {} items in the inventory", item.getName(), item.getQuantity());

        return item.getQuantity();
    }

    @Override
    public Inventory getItem(final Long itemCode) {
        log.info("Retrieving item in the inventory");
        InventoryEntity item = repository.findById(itemCode).orElse(null);
        assert item != null;

        return mapper.map(item);
    }

    @Override
    public Iterable<Inventory> getAllItems() {
        Iterable<InventoryEntity> items = repository.findAll();

        return mapper.map(items);
    }

    private PurchaseResponse processPurchase(final Inventory item, final int totalAmount) {
        deductQuantity(item);
        int change = totalAmount - item.getUnitPrice();
        deductChange(changeService, change);
        log.info("Transaction successful");

        return PurchaseResponse.builder()
                .responseMessage("Transaction successful. Collect item and change of R"+ change)
                .change(change).build();
    }

    private void deductQuantity(final Inventory item) {
        log.info("Deduct quantity from product");
        Integer deduct = item.getQuantity() - 1;
        item.setQuantity(deduct);

        addItem(item);
    }
}
