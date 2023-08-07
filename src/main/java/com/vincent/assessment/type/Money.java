package com.vincent.assessment.type;

public enum Money {
    R1(1), R2(2), R5(5), R10(10), R20(20);
    private int denomination;

    private Money(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

}
