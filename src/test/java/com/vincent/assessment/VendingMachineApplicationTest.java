package com.vincent.assessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class VendingMachineApplicationTest {

    @Autowired
    private VendingMachineApplication application;

    @Test
    void contextLoads() {
        assertThat(application).isNotNull();
    }
}