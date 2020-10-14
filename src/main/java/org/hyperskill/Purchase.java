package org.hyperskill;

import java.util.Objects;

public class Purchase {

    private final String name;
    private final double price;

    public Purchase(String name, double price) {
        if (name == null || name.isBlank()){
            name = "Unknown";
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.price, price) == 0 &&
                name.equals(purchase.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
