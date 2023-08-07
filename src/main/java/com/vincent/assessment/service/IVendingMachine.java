package com.vincent.assessment.service;

import com.vincent.assessment.type.Money;
import com.vincent.assessment.type.Item;
import com.vincent.assessment.util.Bucket;

import java.util.List;

public interface IVendingMachine {
    long selectItemAndGetPrice(Item item);

    void insertMoney(Money money);

    List<Money> change();

    Bucket<Item, List<Money>> collectItemAndChange();

    void reset();
}
