package com.vincent.assessment.type;

import lombok.Getter;

@Getter
public enum ItemType {

    CHIPS("Chips", 15),
    CHOCOLATES("Chocolates", 15),
    DRINKS_330ML("Drinks 300ml", 15),
    DRINKS_440ML("Drinks 440ml", 17),
    DRINKS_500ML("Drinks 500ml", 20),
    PEANUTS("Peanuts", 7),
    WATER_330ML("Water 330ml", 9),
    WATER_500ML("Water 500ml", 12);

    private final String name;
    private final Integer price;

    ItemType(final String name, final Integer price) {
        this.name = name;
        this.price = price;
    }
}
