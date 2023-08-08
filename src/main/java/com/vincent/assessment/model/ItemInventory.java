package com.vincent.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemInventory {
    private String itemCode;
    private String name;
    private Integer unitPrice;
    private Integer quantity;

}
