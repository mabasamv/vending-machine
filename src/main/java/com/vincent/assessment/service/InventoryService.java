package com.vincent.assessment.service;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import com.vincent.assessment.persistance.repository.InventoryRepository;
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
        log.info("Adding/updating item to the inventory");
        InventoryEntity entityItem = mapper.map(item);

        repository.save(entityItem);
    }

    @Override
    public void addItems(final List<Inventory> items) {
        log.info("Adding multiple items to the inventory");
        List<InventoryEntity> entityItem = mapper.map(items);

        repository.saveAll(entityItem);
    }


    @Override
    public void removeItem(final Long itemCode) {
        log.info("Removing item from the inventory");
        repository.deleteById(itemCode);
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

}
