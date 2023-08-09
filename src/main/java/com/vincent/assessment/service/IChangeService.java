package com.vincent.assessment.service;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.model.MoneyType;

public interface IChangeService {

    Iterable<Change> getAll();

    Change getByDenomination(final MoneyType moneyType);

    void loadChange(Change change);

}
