package com.vincent.assessment.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
   // private ItemEntity item;
    private String quantity;
}
