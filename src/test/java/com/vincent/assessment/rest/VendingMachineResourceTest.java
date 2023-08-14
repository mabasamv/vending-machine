package com.vincent.assessment.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vincent.assessment.TestBase;
import com.vincent.assessment.advice.VendingMachineResourceAdvice;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PurchaseRequest;
import com.vincent.assessment.service.IVendingMachineService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineResourceTest extends TestBase {

    protected MockMvc mockMvc;

    @Mock
    private IVendingMachineService service;

    @InjectMocks
    private VendingMachineResource resource;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resource)
                .setHandlerExceptionResolvers(createExceptionResolver()).build();
    }

    @Test
    public void shouldProcessASuccessfulPurchase() throws Exception {
        List<MoneyType> denominations = new ArrayList<>();
        denominations.add(MoneyType.R5);
        denominations.add(MoneyType.R10);

        PurchaseRequest request = PurchaseRequest.builder()
                .itemCode(1L)
                .denominations(denominations)
                .build();

       given(service.purchase(request)).willReturn(purchaseResponseOK());

        MockHttpServletResponse response = purchase(request);
        //assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private MockHttpServletResponse purchase(PurchaseRequest request) throws Exception {
        String url = "/vending-machine/purchase";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        return mockMvc.perform(post(url)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    private ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(
                    HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(VendingMachineResourceAdvice.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(new VendingMachineResourceAdvice(), method);
            }
        };
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }
}