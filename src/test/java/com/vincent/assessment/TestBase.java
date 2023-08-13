package com.vincent.assessment;

import com.vincent.assessment.model.Change;
import com.vincent.assessment.model.MoneyType;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

    protected List<Change> allChange() {
        List<Change> changeList = new ArrayList<>();

        Change change1 = Change.builder().id(1l)
                .denomination(MoneyType.R1.getDenomination())
                .totalAmount(20)
                .build();

        Change change2 = Change.builder().id(2l)
                .denomination(MoneyType.R2.getDenomination())
                .totalAmount(50)
                .build();

        Change change3 = Change.builder().id(3l)
                .denomination(MoneyType.R5.getDenomination())
                .totalAmount(100)
                .build();

        Change change4 = Change.builder().id(4l)
                .denomination(MoneyType.R10.getDenomination())
                .totalAmount(200)
                .build();

        Change change5 = Change.builder().id(5l)
                .denomination(MoneyType.R20.getDenomination())
                .totalAmount(400)
                .build();

        changeList.add(change1);
        changeList.add(change2);
        changeList.add(change3);
        changeList.add(change4);
        changeList.add(change5);

        return changeList;
    }
}
