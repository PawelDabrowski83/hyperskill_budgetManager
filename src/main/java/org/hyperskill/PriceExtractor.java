package org.hyperskill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceExtractor {
    public static final Pattern PRICE_PATTERN = Pattern.compile("\\$(\\d*)\\.(\\d\\d)");

    public static Price extractPrice(String given) throws IllegalArgumentException {
        if (given == null){
            throw new NullPointerException();
        }
        Matcher matcher = PRICE_PATTERN.matcher(given);
        if (given.isBlank()){
            throw new IllegalArgumentException();
        }
        if (matcher.find()){
            String readDollars = matcher.group(1);
            String readCents = matcher.group(2);
            int dollars = 0;
            int cents = 0;
            try {
                dollars = Integer.parseInt(readDollars);
                cents = Integer.parseInt(readCents);
                if (matcher.find()){
                    throw new IllegalArgumentException();
                }
                return Price.build(dollars, cents);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException();
    }
}
