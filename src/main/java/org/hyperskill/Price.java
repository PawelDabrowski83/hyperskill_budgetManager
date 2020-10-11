package org.hyperskill;

public class Price {
    public static final Price FREE = new Price(0, 0);

    private final int dollars;
    private final int cents;

    public Price(int dollars, int cents) {
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
        this.dollars = dollars;
        this.cents = cents;
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
}
