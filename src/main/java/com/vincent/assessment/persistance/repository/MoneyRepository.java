package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.persistance.entity.MoneyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface MoneyRepository extends CrudRepository<MoneyEntity, Long> {

    @Query(value = "SELECT me FROM MoneyEntity me WHERE me.denomination = :denomination")
    MoneyEntity findByDenomination(@Param("denomination") String denomination);
}
