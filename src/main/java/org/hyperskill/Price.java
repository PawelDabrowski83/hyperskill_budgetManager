package org.hyperskill;

import java.util.Objects;

public class Price {
    public static final Price FREE = new Price(0, 0);

    private final int dollars;
    private final int cents;

    private Price(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public static Price build(int dollars, int cents){
        if (dollars < 0){
            dollars = 0;
        }
        if (cents < 0){
            cents = 0;
        }
        while (cents >= 100){
            cents -= 100;
            dollars++;
        }
        return new Price(dollars, cents);
    }

    public int[] getValue(){
        return new int[]{dollars, cents};
    }

    public Price addPrice(Price price){
        int cents = this.cents + price.cents;
        int dollars = 0;
        while (cents >= 100){
            cents -= 100;
            dollars++;
        }
        dollars += this.dollars + price.dollars;
        return new Price(dollars, cents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return dollars == price.dollars &&
                cents == price.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dollars, cents);
    }

    @Override
    public String toString() {
        return "Price{" +
                "dollars=" + dollars +
                ", cents=" + cents +
                '}';
    }

    public double getDecimal(){
        return dollars + cents * 0.01;
    }
}
