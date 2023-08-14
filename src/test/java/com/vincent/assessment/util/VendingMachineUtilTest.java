package com.vincent.assessment.util;

import com.vincent.assessment.TestBase;
import com.vincent.assessment.model.ErrorResponse;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.service.IPettyCashService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
@PrepareForTest(VendingMachineUtil.class)
public class VendingMachineUtilTest extends TestBase {

    @MockBean
    private IPettyCashService changeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCalculateTotalAmount() {
        List<MoneyType> amount = Arrays.asList(MoneyType.R5, MoneyType.R10, MoneyType.R10);
        int totalAmount = VendingMachineUtil.totalAmount(amount);

        assertEquals(25, totalAmount);
    }

    @Test
    public void shouldCreateErrorResponse() {
        ErrorResponse errorResponse = VendingMachineUtil.getErrorResponse("message", "status");
        assertNotNull(errorResponse);
        assertEquals("message", errorResponse.getMessage());
        assertEquals("status", errorResponse.getStatus());
    }
}