package com.vincent.assessment.service;

import com.vincent.assessment.model.Money;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.persistance.entity.MoneyEntity;
import com.vincent.assessment.persistance.repository.MoneyRepository;
import com.vincent.assessment.util.MoneyMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MoneyService implements IMoneyService {

    private final MoneyRepository repository;

    private final MoneyMappers mapper = Mappers.getMapper(MoneyMappers.class);

    public MoneyService(final MoneyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Money> getAll() {
        log.info("Retrieving all amounts configured in the database");
        Iterable<MoneyEntity> iterable = repository.findAll();

        return mapper.map(iterable);
    }

    @Override
    public Money getByDenomination(final MoneyType moneyType) {
        MoneyEntity entity = repository.findByDenomination(moneyType.getDenomination());
        Money money = mapper.map(entity);

        log.info("Denomination: {} - Amount: {}", moneyType.getDenomination(), money.getAmount());

        return money;
    }
}
