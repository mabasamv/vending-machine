package com.vincent.assessment.util;

import com.vincent.assessment.model.ItemInventory;
import com.vincent.assessment.persistance.entity.ItemInventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VendingMachineMappers {

    ItemInventoryEntity mapToEntity(ItemInventory item);
    ItemInventory mapToDTO(ItemInventoryEntity item);

    Iterable<ItemInventory> map(Iterable<ItemInventoryEntity> iterable);
}
