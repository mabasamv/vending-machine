package com.vincent.assessment.util;

import com.vincent.assessment.model.Inventory;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T12:00:32+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class InventoryMappersImpl implements InventoryMappers {

    @Override
    public InventoryEntity map(Inventory source) {

        InventoryEntity inventoryEntity = new InventoryEntity();

        if ( source != null ) {
            if ( source.getItemCode() != null ) {
                inventoryEntity.setItemCode( Long.parseLong( source.getItemCode() ) );
            }
            inventoryEntity.setName( source.getName() );
            inventoryEntity.setUnitPrice( source.getUnitPrice() );
            inventoryEntity.setQuantity( source.getQuantity() );
        }

        return inventoryEntity;
    }

    @Override
    public List<InventoryEntity> map(List<Inventory> source) {
        if ( source == null ) {
            return new ArrayList<InventoryEntity>();
        }

        List<InventoryEntity> list = new ArrayList<InventoryEntity>( source.size() );
        for ( Inventory inventory : source ) {
            list.add( map( inventory ) );
        }

        return list;
    }

    @Override
    public Inventory map(InventoryEntity source) {

        Inventory inventory = new Inventory();

        if ( source != null ) {
            if ( source.getItemCode() != null ) {
                inventory.setItemCode( String.valueOf( source.getItemCode() ) );
            }
            inventory.setName( source.getName() );
            inventory.setUnitPrice( source.getUnitPrice() );
            inventory.setQuantity( source.getQuantity() );
        }

        return inventory;
    }

    @Override
    public Iterable<Inventory> map(Iterable<InventoryEntity> source) {
        if ( source == null ) {
            return new ArrayList<Inventory>();
        }

        ArrayList<Inventory> iterable = new ArrayList<Inventory>();
        for ( InventoryEntity inventoryEntity : source ) {
            iterable.add( map( inventoryEntity ) );
        }

        return iterable;
    }
}
