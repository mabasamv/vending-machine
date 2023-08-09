package com.vincent.assessment.service;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.persistance.entity.ChangeEntity;
import com.vincent.assessment.persistance.repository.ChangeRepository;
import com.vincent.assessment.util.ChangeMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChangeService implements IChangeService {

    private final ChangeRepository repository;

    private final ChangeMappers mapper = Mappers.getMapper(ChangeMappers.class);

    public ChangeService(final ChangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Change> getAll() {
        log.info("Retrieving all amounts configured in the database");
        Iterable<ChangeEntity> iterable = repository.findAll();

        return mapper.map(iterable);
    }

    @Override
    public Change getByDenomination(final MoneyType moneyType) {
        ChangeEntity entity = repository.findByDenomination(moneyType.getDenomination());
        Change change = mapper.map(entity);

        log.info("Denomination: {} - Amount: {}", moneyType.getDenomination(), change.getTotalAmount());

        return change;
    }

    @Override
    public void loadChange(final Change change) {
        log.info("Loading change");
        ChangeEntity entity = mapper.map(change);

        repository.save(entity);
    }
}
