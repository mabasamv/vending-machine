package com.vincent.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PettyCash {

    private Long id;
    private String denomination;
    private Integer totalAmount;
}
