package com.vincent.assessment.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InventoryEntity {

    @Id
    @Column(name = "item_code", nullable = false)
    private Long itemCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Column(name = "quantity")
    private Integer quantity;
}
