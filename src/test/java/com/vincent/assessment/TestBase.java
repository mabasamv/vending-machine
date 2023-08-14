package com.vincent.assessment;

import com.vincent.assessment.model.MoneyType;
import com.vincent.assessment.model.PettyCash;
import com.vincent.assessment.model.PurchaseResponse;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

    protected List<PettyCash> allChange() {
        List<PettyCash> pettyCashList = new ArrayList<>();

        PettyCash pettyCash1 = PettyCash.builder().id(1l)
                .denomination(MoneyType.R1.getDenomination())
                .totalAmount(20)
                .build();

        PettyCash pettyCash2 = PettyCash.builder().id(2l)
                .denomination(MoneyType.R2.getDenomination())
                .totalAmount(50)
                .build();

        PettyCash pettyCash3 = PettyCash.builder().id(3l)
                .denomination(MoneyType.R5.getDenomination())
                .totalAmount(100)
                .build();

        PettyCash pettyCash4 = PettyCash.builder().id(4l)
                .denomination(MoneyType.R10.getDenomination())
                .totalAmount(200)
                .build();

        PettyCash pettyCash5 = PettyCash.builder().id(5l)
                .denomination(MoneyType.R20.getDenomination())
                .totalAmount(400)
                .build();

        pettyCashList.add(pettyCash1);
        pettyCashList.add(pettyCash2);
        pettyCashList.add(pettyCash3);
        pettyCashList.add(pettyCash4);
        pettyCashList.add(pettyCash5);

        return pettyCashList;
    }
/*    protected PurchaseRequest purchaseRequest() {
        return PurchaseRequest.builder()
                .
                .build();
    }*/

    protected PurchaseResponse purchaseResponseOK() {
        return PurchaseResponse.builder()
                .responseMessage("Purchase successful")
                .change(5)
                .build();
    }
}
