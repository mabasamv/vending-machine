package com.vincent.assessment.service;

import com.vincent.assessment.config.VendingMachineFactory;
import com.vincent.assessment.exception.NotSufficientChangeException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.type.Money;
import com.vincent.assessment.type.Item;
import com.vincent.assessment.util.Bucket;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {
    private static IVendingMachine vm;

    @BeforeClass
    public static void setUp() {
        vm = VendingMachineFactory.createVendingMachine();
    }

    @AfterClass
    public static void tearDown() {
        vm = null;
    }

    @Test
    public void testBuyItemWithExactPrice() {
        //select item, price in cents
        long price = vm.selectItemAndGetPrice(Item.COKE);
        // price should be Coke's price
        assertEquals(Item.COKE.getPrice(), price);
        //25 cents paid
        vm.insertMoney(Money.R10);
        Bucket<Item, List<Money>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Money> change = bucket.getSecond();
        //should be Coke
        assertEquals (Item.COKE, item);
        //there should not be any change
        assertTrue(change.isEmpty());
    }

    @Test
    public void testBuyItemWithMorePrice() {
        long price = vm.selectItemAndGetPrice(Item.SODA);
        System.out.println(price);
        assertEquals(Item.SODA.getPrice(), price);
        vm.insertMoney(Money.R20);
        vm.insertMoney(Money.R10);
        vm.insertMoney(Money.R5);
        Bucket<Item, List<Money>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Money> change = bucket.getSecond();
        //should be Coke
        assertEquals (Item.SODA, item);
        //there should not be any change
        assertTrue(!change.isEmpty());
        //comparing change
        assertEquals(35 - Item.SODA.getPrice(), getTotal(change));
    }

    @Test
    public void testRefund() {
        long price = vm.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(Item.PEPSI.getPrice(), price);
        vm.insertMoney(Money.R5);
        vm.insertMoney(Money.R2);
        vm.insertMoney(Money.R1);
        vm.insertMoney(Money.R10);
        assertEquals(18, getTotal(vm.change()));
    }

    @Test(expected = SoldOutException.class)
    public void testSoldOut() {
        for (int i = 0; i < 5; i++) {
            vm.selectItemAndGetPrice(Item.COKE);
            vm.insertMoney(Money.R10);
            vm.collectItemAndChange();
        }
    }

    @Test(expected = NotSufficientChangeException.class)
    public void testNotSufficientChangeException() {
        for (int i = 0; i < 5; i++) {
            vm.selectItemAndGetPrice(Item.SODA);
            vm.insertMoney(Money.R10);
            vm.insertMoney(Money.R10);
            vm.insertMoney(Money.R5);
            vm.collectItemAndChange();

            vm.selectItemAndGetPrice(Item.PEPSI);
            vm.insertMoney(Money.R10);
            vm.insertMoney(Money.R10);
            vm.collectItemAndChange();
        }
    }

    @Test(expected = SoldOutException.class)
    public void testReset() {
        IVendingMachine vmachine = VendingMachineFactory.createVendingMachine();
        vmachine.reset();
        vmachine.selectItemAndGetPrice(Item.COKE);
    }

    private long getTotal(List<Money> change) {
        long total = 0;
        for (Money c : change) {
            total = total + c.getDenomination();
        }
        return total;
    }

}