package com.vincent.assessment.util;

import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.persistance.entity.PettyCashEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PettyCashMappers {

    PettyCash map(PettyCashEntity source);
    PettyCashEntity map(PettyCash source);
    Iterable<PettyCash> map(Iterable<PettyCashEntity> source);
}
