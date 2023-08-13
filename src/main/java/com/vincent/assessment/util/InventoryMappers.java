package com.vincent.assessment.util;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface InventoryMappers {

    InventoryEntity map(Inventory source);
    List<InventoryEntity> map(List<Inventory> source);
    Inventory map(InventoryEntity source);
    Iterable<Inventory> map(Iterable<InventoryEntity> source);
}
