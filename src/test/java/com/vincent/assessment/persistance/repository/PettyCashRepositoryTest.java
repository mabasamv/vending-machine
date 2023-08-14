package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.TestBase;
import com.vincent.assessment.persistance.entity.PettyCashEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PettyCashRepositoryTest extends TestBase {

    @Mock
    private PettyCashRepository repository;

    private PettyCashEntity entity;

    @Before
    public void setUp() {
        entity = pettyCash() ;
    }

    @Test
    public void shouldFindByDenomination() {
        when(repository.findByDenomination(any())).thenReturn(pettyCash());

        PettyCashEntity pettyCash = repository.findByDenomination("R1");
        assertEquals(entity.getId(), pettyCash.getId());
        assertEquals(entity.getDenomination(), pettyCash.getDenomination());
        assertEquals(entity.getTotalAmount(), pettyCash.getTotalAmount());
        repository.deleteAll();;
    }
}