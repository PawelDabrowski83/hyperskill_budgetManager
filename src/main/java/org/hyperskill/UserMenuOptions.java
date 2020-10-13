package org.hyperskill;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserMenuOptions {
    ADD_INCOME("1"),
    ADD_PURCHASE("2"),
    SHOW_LIST_OF_PURCHASES("3"),
    BALANCE("4"),
    EXIT("0"),
    DEFAULT("");

    private final String value;
    private static final Map<String, UserMenuOptions> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(UserMenuOptions::getValue, Function.identity()));

    UserMenuOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserMenuOptions getByValue(String value){
        return lookUp.getOrDefault(value, UserMenuOptions.DEFAULT);
    }
}

