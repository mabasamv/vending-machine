package com.vincent.assessment.util;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.service.IChangeService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@UtilityClass
public class VendingMachineUtil {

    public static Integer totalAmount(final List<MoneyType> amount) {
        int totalAmount = 0;
        for (MoneyType money : amount) {
            totalAmount = totalAmount + money.getAmount();
        }
        return totalAmount;
    }

    public static Integer totalChange(final IChangeService changeService) {
        Iterable<Change> iterableChange = changeService.getAll();

        int totalChange = 0;
        for (Change change : iterableChange) {
            totalChange = totalChange + change.getTotalAmount();
        }
        return totalChange;
    }

    public static void deductChange(final IChangeService changeService, final int totalChange) {
        log.info("Change R{}", totalChange);
        Iterable<Change> iterableChange = changeService.getAll();

        for (Change change : iterableChange) {
            if (change.getTotalAmount() >= totalChange) {
                change.setTotalAmount(change.getTotalAmount() - totalChange);

                changeService.loadChange(change);
                break;
            }
        }
    }
}
