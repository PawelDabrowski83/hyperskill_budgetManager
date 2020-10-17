package org.hyperskill;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PurchaseCategories {
    FOOD("1", "Food"),
    CLOTHES("2", "Clothes"),
    ENTERTAINMENT("3", "Entertainment"),
    OTHER("4", "Other"),
    ALL("5", "All"),
    BACK("6", "Back"),
    DEFAULT("0", "Default");

    private final String value;
    private final String label;
    private static final Map<String, PurchaseCategories> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(PurchaseCategories::getValue, Function.identity()));

    PurchaseCategories(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static PurchaseCategories getByValue(String value){
        return lookUp.getOrDefault(value, PurchaseCategories.DEFAULT);
    }
}
