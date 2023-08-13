package com.vincent.assessment.service;

import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PettyCash;

public interface IPettyCashService {

    Iterable<PettyCash> getAll();

    PettyCash getByDenomination(final MoneyType moneyType);

    void loadCash(final PettyCash pettyCash);

}
