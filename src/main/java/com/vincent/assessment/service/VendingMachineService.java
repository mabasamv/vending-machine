package com.vincent.assessment.service;

import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.NotSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.type.MoneyType;
import com.vincent.assessment.type.ItemType;
import com.vincent.assessment.util.Bucket;
import com.vincent.assessment.util.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class VendingMachineService implements IVendingMachineService {

    private Inventory<MoneyType> cashInventory = new Inventory<MoneyType>();
    private Inventory<ItemType> itemInventory = new Inventory<ItemType>();
    private long totalSales;
    private ItemType currentItem;
    private long currentBalance;


    public VendingMachineService() {
        initialize();
    }

    private void initialize() {
        //initialize machine with 10 money of each denomination
        //and 5 cans of each Item
        for (MoneyType c : MoneyType.values()) {
            cashInventory.put(c, 10);
        }

        for (ItemType i : ItemType.values()) {
            itemInventory.put(i, 10);
        }

    }

    @Override
    public long selectItemAndGetPrice(ItemType item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
            printStats();
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    @Override
    public void insertMoney(MoneyType money) {
        currentBalance = currentBalance + money.getAmount();
        log.info("R{} added to the cash inventory, current balance is R{}", money.getAmount(), currentBalance);
        cashInventory.add(money);
    }

    @Override
    public Bucket<ItemType, List<MoneyType>> collectItemAndChange() {
        ItemType item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<MoneyType> change = collectChange();
        log.info("Change is R{}", change);

        return new Bucket<ItemType, List<MoneyType>>(item, change);
    }

    private ItemType collectItem() throws NotSufficientChangeException, NotFullPaidException {
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

    private List<MoneyType> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<MoneyType> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    @Override
    public List<MoneyType> change() {
        List<MoneyType> refund = getChange(currentBalance);
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


    private List<MoneyType> getChange(long amount) throws NotSufficientChangeException {
        List<MoneyType> changes = Collections.EMPTY_LIST;

        if (amount > 0) {
            changes = new ArrayList<MoneyType>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= MoneyType.R20.getAmount() && cashInventory.hasItem(MoneyType.R20)) {
                    changes.add(MoneyType.R20);
                    balance = balance - MoneyType.R20.getAmount();
                    continue;
                } else if (balance >= MoneyType.R10.getAmount() && cashInventory.hasItem(MoneyType.R10)) {
                    changes.add(MoneyType.R10);
                    balance = balance - MoneyType.R10.getAmount();
                    continue;

                } else if (balance >= MoneyType.R5.getAmount() && cashInventory.hasItem(MoneyType.R5)) {
                    changes.add(MoneyType.R5);
                    balance = balance - MoneyType.R5.getAmount();
                    continue;

                } else if (balance >= MoneyType.R2.getAmount() && cashInventory.hasItem(MoneyType.R2)) {
                    changes.add(MoneyType.R2);
                    balance = balance - MoneyType.R2.getAmount();
                    continue;

                } else if (balance >= MoneyType.R1.getAmount() && cashInventory.hasItem(MoneyType.R1)) {
                    changes.add(MoneyType.R1);
                    balance = balance - MoneyType.R1.getAmount();
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
        log.info("Total Sales : {}", totalSales);
        log.info("Current Item Inventory : {}", itemInventory);
        log.info("Current Cash Inventory : {}", cashInventory);
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

    private void updateCashInventory(List<MoneyType> change) {
        for (MoneyType c : change) {
            cashInventory.deduct(c);
        }
    }

    public long getTotalSales() {
        return totalSales;
    }
}
