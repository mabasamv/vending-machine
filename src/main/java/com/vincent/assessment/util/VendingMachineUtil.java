package com.vincent.assessment.util;

import com.vincent.assessment.model.ErrorResponse;
import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.service.IPettyCashService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
@UtilityClass
public class VendingMachineUtil {

    public static final String NOT_FULLY_PAID = "NOT_FULLY_PAID";
    public static final String NO_SUFFICIENT_CHANGE = "NO_SUFFICIENT_CHANGE";
    public static final String SOLD_OUT = "SOLD_OUT";

    public static Integer totalAmount(final List<MoneyType> amount) {
        int totalAmount = 0;
        for (MoneyType money : amount) {
            totalAmount = totalAmount + money.getAmount();
        }
        return totalAmount;
    }

    public static Integer totalChange(final IPettyCashService changeService) {
        Iterable<PettyCash> iterableChange = changeService.getAll();

        int totalChange = 0;
        for (PettyCash pettyCash : iterableChange) {
            totalChange = totalChange + pettyCash.getTotalAmount();
        }
        return totalChange;
    }

    public static void deductChange(final IPettyCashService changeService, final int totalChange) {
        log.info("Change R{}", totalChange);
        Iterable<PettyCash> iterableChange = changeService.getAll();

        for (PettyCash pettyCash : iterableChange) {
            if (pettyCash.getTotalAmount() >= totalChange) {
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() - totalChange);

                changeService.loadCash(pettyCash);
                break;
            }
        }
    }

    public void saveCash(final IPettyCashService pettyCashService, final List<MoneyType> denominations) {
        for (MoneyType denomination : denominations) {
            if (denomination.getDenomination().equals(MoneyType.R1.getDenomination())) {
                PettyCash pettyCash = pettyCashService.getByDenomination(denomination);
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() + MoneyType.R1.getAmount());

                pettyCashService.loadCash(pettyCash);
            }

            if (denomination.getDenomination().equals(MoneyType.R2.getDenomination())) {
                PettyCash pettyCash = pettyCashService.getByDenomination(denomination);
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() + MoneyType.R2.getAmount());

                pettyCashService.loadCash(pettyCash);
            }

            if (denomination.getDenomination().equals(MoneyType.R5.getDenomination())) {
                PettyCash pettyCash = pettyCashService.getByDenomination(denomination);
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() + MoneyType.R5.getAmount());

                pettyCashService.loadCash(pettyCash);
            }

            if (denomination.getDenomination().equals(MoneyType.R10.getDenomination())) {
                PettyCash pettyCash = pettyCashService.getByDenomination(denomination);
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() + MoneyType.R10.getAmount());

                pettyCashService.loadCash(pettyCash);
            }

            if (denomination.getDenomination().equals(MoneyType.R20.getDenomination())) {
                PettyCash pettyCash = pettyCashService.getByDenomination(denomination);
                pettyCash.setTotalAmount(pettyCash.getTotalAmount() + MoneyType.R20.getAmount());

                pettyCashService.loadCash(pettyCash);
            }
        }
    }

    public static ErrorResponse getErrorResponse(final String message, final String status) {
        return ErrorResponse.builder()
                .status(status)
                .timestamp(new Date().toString())
                .message(message)
                .build();
    }
}
