package com.vincent.assessment.type;

import lombok.Getter;

@Getter
public enum MoneyType {

    R1("R1", 1), R2("R2", 2), R5("R5", 5), R10("R10", 10), R20("R20", 20);
    private final String denomination;
    private final Integer amount;

    MoneyType(final String denomination, final Integer amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

}
