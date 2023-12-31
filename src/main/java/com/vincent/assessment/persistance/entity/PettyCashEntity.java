package com.vincent.assessment.persistance.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "petty_cash")
public class PettyCashEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "denomination", nullable = false)
    private String denomination;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;
}
