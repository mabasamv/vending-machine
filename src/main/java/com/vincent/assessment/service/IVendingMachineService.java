package com.vincent.assessment.service;

import com.vincent.assessment.type.MoneyType;
import com.vincent.assessment.type.ItemType;
import com.vincent.assessment.util.Bucket;

import java.util.List;

public interface IVendingMachineService {
    long selectItemAndGetPrice(ItemType item);

    void insertMoney(MoneyType money);

    List<MoneyType> change();

    Bucket<ItemType, List<MoneyType>> collectItemAndChange();

    void reset();
}
