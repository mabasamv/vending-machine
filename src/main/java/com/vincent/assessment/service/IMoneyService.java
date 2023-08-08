package com.vincent.assessment.service;

import com.vincent.assessment.model.Money;
import com.vincent.assessment.model.MoneyType;

public interface IMoneyService {

    Iterable<Money> getAll();

    Money getByDenomination(final MoneyType moneyType);
}
