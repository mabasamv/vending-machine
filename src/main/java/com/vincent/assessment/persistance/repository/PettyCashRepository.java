package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.persistance.entity.PettyCashEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PettyCashRepository extends CrudRepository<PettyCashEntity, Long> {

    @Query(value = "SELECT me FROM PettyCashEntity me WHERE me.denomination = :denomination")
    PettyCashEntity findByDenomination(@Param("denomination") String denomination);
}
