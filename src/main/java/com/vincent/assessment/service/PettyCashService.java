package com.vincent.assessment.service;

import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.persistance.entity.PettyCashEntity;
import com.vincent.assessment.persistance.repository.PettyCashRepository;
import com.vincent.assessment.util.PettyCashMappers;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PettyCashService implements IPettyCashService {

    private final PettyCashRepository repository;

    private final PettyCashMappers mapper = Mappers.getMapper(PettyCashMappers.class);

    public PettyCashService(final PettyCashRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<PettyCash> getAll() {
        return mapper.map(repository.findAll());
    }

    @Override
    public PettyCash getByDenomination(final MoneyType moneyType) {
        PettyCashEntity entity = repository.findByDenomination(moneyType.getDenomination());
        PettyCash pettyCash = mapper.map(entity);

        log.info("Denomination: {} - Amount: {}", moneyType.getDenomination(), pettyCash.getTotalAmount());

        return pettyCash;
    }

    @Override
    public void loadCash(final PettyCash pettyCash) {
        PettyCashEntity entity = mapper.map(pettyCash);
        repository.save(entity);
    }
}
