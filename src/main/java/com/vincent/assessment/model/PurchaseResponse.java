package com.vincent.assessment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseResponse {
    private String responseMessage;
    private Integer change;
}
