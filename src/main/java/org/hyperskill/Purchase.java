package org.hyperskill;

import java.util.Objects;

public class Purchase {

    private final String name;
    private final double price;
    private final PurchaseCategory category;

    public Purchase(String name, double price, PurchaseCategory category) {
        if (name == null || name.isBlank()){
            name = "Unknown";
        }
        if (category == null){
            category = PurchaseCategory.DEFAULT;
        }
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public PurchaseCategory getCategory(){
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.price, price) == 0 &&
                name.equals(purchase.name) &&
                category == purchase.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }
}
