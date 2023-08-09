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
@Table(name = "change")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChangeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "denomination", nullable = false)
    private String denomination;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;
}
