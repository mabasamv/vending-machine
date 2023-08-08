package com.vincent.assessment.model;

import com.vincent.assessment.type.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PurchaseRequest {

    private Long itemCode;
    private List<MoneyType> denominations;
}
