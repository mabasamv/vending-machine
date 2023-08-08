package com.vincent.assessment.service;

import com.vincent.assessment.persistance.entity.MoneyEntity;
import com.vincent.assessment.type.MoneyType;

import java.util.List;

public interface IMoneyService {

    Iterable<MoneyEntity> getAmounts();

    Integer getAmountByDenomination(final MoneyType moneyType);
}
