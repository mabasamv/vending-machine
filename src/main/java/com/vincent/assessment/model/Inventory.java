package com.vincent.assessment.model;

import lombok.Data;

@Data
public class Inventory {
    private String itemCode;
    private String name;
    private Integer unitPrice;
    private Integer quantity;

}
