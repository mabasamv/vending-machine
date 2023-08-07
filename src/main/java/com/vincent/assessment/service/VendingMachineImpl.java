package com.vincent.assessment.service;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NotSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.type.Money;
import com.vincent.assessment.type.Item;
import com.vincent.assessment.util.Bucket;
import com.vincent.assessment.util.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class VendingMachineImpl implements IVendingMachine {

    private Inventory<Money> cashInventory = new Inventory<Money>();
    private Inventory<Item> itemInventory = new Inventory<Item>();
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize() {
        //initialize machine with 10 money of each denomination
        //and 5 cans of each Item
        for (Money c : Money.values()) {
            cashInventory.put(c, 10);
        }

        for (Item i : Item.values()) {
            itemInventory.put(i, 10);
        }

    }

    @Override
    public long selectItemAndGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    @Override
    public void insertMoney(Money money) {
        currentBalance = currentBalance + money.getDenomination();
        cashInventory.add(money);
    }

    @Override
    public Bucket<Item, List<Money>> collectItemAndChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<Money> change = collectChange();

        return new Bucket<Item, List<Money>>(item, change);
    }

    private Item collectItem() throws NotSufficientChangeException,
            NotFullPaidException {
        if (isFullPaid()) {
            if (hasSufficientChange()) {
                itemInventory.deduct(currentItem);
                return currentItem;
            }
            throw new NotSufficientChangeException("Not Sufficient change in Inventory");

        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
    }

    private List<Money> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Money> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    @Override
    public List<Money> change() {
        List<Money> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }


    private boolean isFullPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        }
        return false;
    }


    private List<Money> getChange(long amount) throws NotSufficientChangeException {
        List<Money> changes = Collections.EMPTY_LIST;

        if (amount > 0) {
            changes = new ArrayList<Money>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Money.R20.getDenomination() && cashInventory.hasItem(Money.R20)) {
                    changes.add(Money.R20);
                    balance = balance - Money.R20.getDenomination();
                    continue;
                } else if (balance >= Money.R10.getDenomination() && cashInventory.hasItem(Money.R10)) {
                    changes.add(Money.R10);
                    balance = balance - Money.R10.getDenomination();
                    continue;

                } else if (balance >= Money.R5.getDenomination() && cashInventory.hasItem(Money.R5)) {
                    changes.add(Money.R5);
                    balance = balance - Money.R5.getDenomination();
                    continue;

                } else if (balance >= Money.R2.getDenomination() && cashInventory.hasItem(Money.R2)) {
                    changes.add(Money.R2);
                    balance = balance - Money.R2.getDenomination();
                    continue;

                } else if (balance >= Money.R1.getDenomination() && cashInventory.hasItem(Money.R1)) {
                    changes.add(Money.R1);
                    balance = balance - Money.R1.getDenomination();
                    continue;

                } else {
                    throw new NotSufficientChangeException("NotSufficientChange, Please try another product!");
                }
            }
        }

        return changes;
    }

    @Override
    public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + cashInventory);
    }


    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (NotSufficientChangeException nsce) {
            return hasChange = false;
        }

        return hasChange;
    }

    private void updateCashInventory(List<Money> change) {
        for (Money c : change) {
            cashInventory.deduct(c);
        }
    }

    public long getTotalSales() {
        return totalSales;
    }
}
