package com.vincent.assessment.service;

import com.vincent.assessment.persistance.entity.MoneyEntity;
import com.vincent.assessment.persistance.repository.MoneyRepository;
import com.vincent.assessment.type.MoneyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MoneyService implements IMoneyService {

    private final MoneyRepository repository;

    public MoneyService(final MoneyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<MoneyEntity> getAmounts() {
        log.info("Retrieving all amounts configured in the database");
        return repository.findAll();
    }

    @Override
    public Integer getAmountByDenomination(final MoneyType moneyType) {
        Integer amount = repository.findByDenomination(moneyType.getDenomination());

        log.info("Denomination: {} - Amount: {}", moneyType.getDenomination(), amount);

        return amount;
    }
}
