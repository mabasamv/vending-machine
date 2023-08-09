package com.vincent.assessment.util;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.persistance.entity.ChangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ChangeMappers {

    Change map(ChangeEntity source);
    ChangeEntity map(Change source);
    Iterable<Change> map(Iterable<ChangeEntity> source);
}
