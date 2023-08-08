package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.persistance.entity.InventoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryEntity, Long> {
}
