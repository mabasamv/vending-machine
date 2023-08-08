package com.vincent.assessment.util;

import com.vincent.assessment.model.Money;
import com.vincent.assessment.persistance.entity.MoneyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface MoneyMappers {

    Money map(MoneyEntity source);
    Iterable<Money> map(Iterable<MoneyEntity> source);
}
