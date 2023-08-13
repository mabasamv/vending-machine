package com.vincent.assessment.service;

import com.vincent.assessment.exception.NoSufficientChangeException;
import com.vincent.assessment.exception.NotFullPaidException;
import com.vincent.assessment.exception.SoldOutException;
import com.vincent.assessment.exception.VendingMachineException;
import com.vincent.assessment.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vincent.assessment.util.VendingMachineUtil.*;

@Slf4j
@Service
public class VendingMachineService implements IVendingMachineService {

    private final InventoryService inventoryService;
    private final IPettyCashService pettyCashService;

    public VendingMachineService(final InventoryService inventoryService, final IPettyCashService pettyCashService) {
        this.inventoryService = inventoryService;
        this.pettyCashService = pettyCashService;
    }

    @SneakyThrows
    @Override
    public PurchaseResponse purchase(final PurchaseRequest purchaseRequest) {
        try {
            log.info("Purchase item");
            Inventory item = inventoryService.getItem(purchaseRequest.getItemCode());
            int quantity = inventoryService.getQuantity(purchaseRequest.getItemCode());

            if (quantity > 0) {
                List<MoneyType> amount = purchaseRequest.getDenominations();

                int totalAmount = totalAmount(amount);
                if (totalAmount >= item.getUnitPrice()) {
                    if (totalAmount > totalChange(pettyCashService))
                        throw new NoSufficientChangeException("No sufficient change in vending machine, transaction will be cancelled");
                    else {
                        saveCash(pettyCashService, purchaseRequest.getDenominations());
                        return processPurchase(item, totalAmount);
                    }
                } else
                    throw new NotFullPaidException("Insufficient amount provided for purchase");
            } else
                throw new SoldOutException("Item sold out");
        } catch (NoSufficientChangeException ex) {
            throw new NoSufficientChangeException(ex.getMessage());
        } catch (NotFullPaidException ex) {
            throw new NotFullPaidException(ex.getMessage());
        } catch (SoldOutException ex) {
            throw new SoldOutException(ex.getMessage());
        } catch (Exception ex) {
            throw new VendingMachineException(ex.getMessage());
        }
    }

    private PurchaseResponse processPurchase(final Inventory item, final int totalAmount) {
        deductQuantity(item);
        int change = totalAmount - item.getUnitPrice();
        deductChange(pettyCashService, change);
        log.info("Transaction successful");

        return PurchaseResponse.builder()
                .responseMessage("Transaction successful. Collect item and change of R" + change)
                .change(change).build();
    }

    private void deductQuantity(final Inventory item) {
        log.info("Deduct quantity from product");
        Integer deduct = item.getQuantity() - 1;
        item.setQuantity(deduct);

        inventoryService.addItem(item);
    }
}
