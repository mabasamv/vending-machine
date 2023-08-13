package com.vincent.assessment.service;

import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.model.MoneyType;

public interface IPettyCashService {

    Iterable<PettyCash> getAll();

    PettyCash getByDenomination(final MoneyType moneyType);

    void loadCash(final PettyCash pettyCash);

}
