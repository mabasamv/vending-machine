package com.vincent.assessment.persistance.repository;

import com.vincent.assessment.persistance.entity.ChangeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChangeRepository extends CrudRepository<ChangeEntity, Long> {

    @Query(value = "SELECT me FROM ChangeEntity me WHERE me.denomination = :denomination")
    ChangeEntity findByDenomination(@Param("denomination") String denomination);
}
