package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.persistance.entity.ItemInventoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemInventoryRepository extends CrudRepository<ItemInventoryEntity, Long> {
}
