package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.TestBase;
import com.vincent.assessment.persistance.entity.InventoryEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryRepositoryTest extends TestBase {

    @Mock
    private InventoryRepository repository;

    private InventoryEntity entity;

    @Before
    public void setUp() {
        entity = inventory();
    }

    @Test
    public void shouldFindById() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(entity));

        Optional<InventoryEntity> inventory = repository.findById(1L);
        assertEquals(entity.getItemCode(), inventory.get().getItemCode());
        assertEquals(entity.getName(), inventory.get().getName());
        assertEquals(entity.getUnitPrice(), inventory.get().getUnitPrice());
        assertEquals(entity.getQuantity(), inventory.get().getQuantity());
        repository.deleteAll();
    }
}