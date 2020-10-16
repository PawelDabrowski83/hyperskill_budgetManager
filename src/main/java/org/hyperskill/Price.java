package org.hyperskill;

import java.util.Objects;

public class Price implements Comparable<Price>{
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

    public static Price build(double number){
        int dollars = (int) Math.floor(number);
        StringBuilder centsAsString = new StringBuilder(String.valueOf(number));
        int indexOfDecimalPoint = centsAsString.toString().indexOf('.');
        centsAsString = new StringBuilder(centsAsString.substring(indexOfDecimalPoint + 1, centsAsString.length()));
        if(centsAsString.length() > 2){
            centsAsString = new StringBuilder(centsAsString.substring(0, 2));
        }
        while (centsAsString.length() < 2){
            centsAsString.append("0");
        }
        int cents = 0;
        try{
            cents = Integer.parseInt(centsAsString.toString());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return build(dollars, cents);
    }

    public int[] getValue(){
        return new int[]{dollars, cents};
    }

    public Price add(Price price){
        int cents = this.cents + price.cents;
        int dollars = 0;
        while (cents >= 100){
            cents -= 100;
            dollars++;
        }
        dollars += this.dollars + price.dollars;
        return new Price(dollars, cents);
    }

    protected Price subtract(Price price){
        if (this.equals(price) || this.compareTo(price) < 0){
            return Price.FREE;
        }
        if (Price.FREE.equals(price)){
            return this;
        }
        int baseCents = this.cents;
        int baseDollars = this.dollars;
        if (baseCents < price.cents){
            baseCents += 100;
            baseDollars--;
        }
        int targetCents = baseCents - price.cents;
        int targetDollars = baseDollars - price.dollars;
        return Price.build(targetDollars, targetCents);
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

    @Override
    public int compareTo(Price o) {
        return Double.compare(this.getDecimal(), o.getDecimal());
    }
}
