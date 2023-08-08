package com.vincent.assessment.model;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequest {

    private Long itemCode;
    private List<MoneyType> denominations;
}
