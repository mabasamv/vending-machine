package com.vincent.assessment.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "money")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MoneyEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "denomination", nullable = false)
    private String denomination;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
