package com.vincent.assessment.type;

public enum Item {
    COKE("Coke", 10), PEPSI("Pepsi", 15), SODA("Soda", 25);

    private String name;
    private int price;

    private Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

}
