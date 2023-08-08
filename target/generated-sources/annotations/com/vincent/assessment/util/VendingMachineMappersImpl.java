package com.vincent.assessment.util;

import com.vincent.assessment.model.ItemInventory;
import com.vincent.assessment.persistance.entity.ItemInventoryEntity;
import java.util.ArrayList;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-08T22:41:40+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class VendingMachineMappersImpl implements VendingMachineMappers {

    @Override
    public ItemInventoryEntity mapToEntity(ItemInventory item) {

        ItemInventoryEntity itemInventoryEntity = new ItemInventoryEntity();

        if ( item != null ) {
            if ( item.getItemCode() != null ) {
                itemInventoryEntity.setItemCode( Long.parseLong( item.getItemCode() ) );
            }
            itemInventoryEntity.setName( item.getName() );
            itemInventoryEntity.setUnitPrice( item.getUnitPrice() );
            itemInventoryEntity.setQuantity( item.getQuantity() );
        }

        return itemInventoryEntity;
    }

    @Override
    public ItemInventory mapToDTO(ItemInventoryEntity item) {

        ItemInventory itemInventory = new ItemInventory();

        if ( item != null ) {
            if ( item.getItemCode() != null ) {
                itemInventory.setItemCode( String.valueOf( item.getItemCode() ) );
            }
            itemInventory.setName( item.getName() );
            itemInventory.setUnitPrice( item.getUnitPrice() );
            itemInventory.setQuantity( item.getQuantity() );
        }

        return itemInventory;
    }

    @Override
    public Iterable<ItemInventory> map(Iterable<ItemInventoryEntity> iterable) {
        if ( iterable == null ) {
            return new ArrayList<ItemInventory>();
        }

        ArrayList<ItemInventory> iterable1 = new ArrayList<ItemInventory>();
        for ( ItemInventoryEntity itemInventoryEntity : iterable ) {
            iterable1.add( mapToDTO( itemInventoryEntity ) );
        }

        return iterable1;
    }
}
