package com.vincent.assessment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PettyCash {

    private Long id;
    private String denomination;
    private Integer totalAmount;
}
