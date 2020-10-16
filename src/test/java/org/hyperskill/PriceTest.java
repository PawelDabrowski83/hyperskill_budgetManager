package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceTest {



    @DisplayName("should getValue() work")
    @ParameterizedTest
    @MethodSource("getValueArgumentsProvider")
    void getValue(int[] expected, Price givenPrice){
        assertArrayEquals(expected, givenPrice.getValue());
    }
    private static Stream<Arguments> getValueArgumentsProvider(){
        return Stream.of(
                Arguments.of(new int[]{0, 0}, Price.FREE),
                Arguments.of(new int[]{10, 19}, Price.build(10, 19)),
                Arguments.of(new int[]{0, 99}, Price.build(0, 99)),
                Arguments.of(new int[]{10, 0}, Price.build(9, 100))
        );
    }

    @DisplayName("should Price.add() add two prices")
    @ParameterizedTest
    @MethodSource("addArgumentsProvider")
    void addPrice(Price expected, Price base, Price added){
        assertEquals(expected, base.add(added));
    }
    private static Stream<Arguments> addArgumentsProvider(){
        return Stream.of(
                Arguments.of(Price.FREE, Price.FREE, Price.FREE),
                Arguments.of(Price.build(1, 0), Price.build(0, 50), Price.build(0, 50)),
                Arguments.of(Price.build(10, 12), Price.FREE, Price.build(10, 12))
        );
    }

    @DisplayName("should getDecimal return decimal value of price")
    @ParameterizedTest
    @MethodSource("getDecimalArgumentsProvider")
    void getDecimal(double expected, Price given){
        assertEquals(expected, given.getDecimal(), 0.01);
    }
    private static Stream<Arguments> getDecimalArgumentsProvider(){
        return Stream.of(
                Arguments.of(0.5, Price.build(0, 50)),
                Arguments.of(0.05, Price.build(0, 5)),
                Arguments.of(5.0, Price.build(5, 0)),
                Arguments.of(1.99, Price.build(1, 99)),
                Arguments.of(0, Price.FREE)
        );
    }

    @DisplayName("should build(double) return valid Price value")
    @ParameterizedTest
    @MethodSource("buildArgumentsProvider")
    void build(Price expected, double given){
        assertEquals(expected, Price.build(given));
    }
    private static Stream<Arguments> buildArgumentsProvider(){
        return Stream.of(
                Arguments.of(Price.FREE, 0),
                Arguments.of(Price.build(12, 10), 12.1),
                Arguments.of(Price.build(0, 99), 0.99),
                Arguments.of(Price.build(7, 79), 7.7999999)
        );
    }

    @DisplayName("should compareTo() work properly")
    @ParameterizedTest
    @MethodSource("compareToArgumentsProvider")
    void compareTo(int expected, Price p1, Price p2){
        assertEquals(expected, p1.compareTo(p2));
    }
    private static Stream<Arguments> compareToArgumentsProvider(){
        return Stream.of(
                Arguments.of(0, Price.FREE, Price.FREE),
                Arguments.of(0, Price.build(0, 10), Price.build(0, 10)),
                Arguments.of(1, Price.build(1, 99), Price.build(0, 99)),
                Arguments.of(-1, Price.build(1, 1), Price.build(1, 10))
        );
    }

    @DisplayName("should subtract(Price) return calculation result")
    @ParameterizedTest
    @MethodSource("subtractArgumentsProvider")
    void subtract(Price expected, Price p1, Price p2){
        assertEquals(expected, p1.subtract(p2));
    }

    private static Stream<Arguments> subtractArgumentsProvider() {
        return Stream.of(
                Arguments.of(Price.FREE, Price.FREE, Price.FREE),
                Arguments.of(Price.build(1, 0), Price.build(1, 0), Price.FREE),
                Arguments.of(Price.FREE, Price.build(1, 99), Price.build(2, 0)),
                Arguments.of(Price.FREE, Price.FREE, Price.build(99, 12)),
                Arguments.of(Price.build(17, 19), Price.build(18, 20), Price.build(1, 1)),
                Arguments.of(Price.FREE, Price.build(9, 99), Price.build(9, 99)),
                Arguments.of(Price.FREE, Price.build(9, 13), Price.build(9, 20)),
                Arguments.of(Price.build(1, 15), Price.build(1, 15), Price.FREE),
                Arguments.of(Price.build(Integer.MAX_VALUE - 1, 0), Price.build(Integer.MAX_VALUE, 0), Price.build(1, 0)),
                Arguments.of(Price.build(1, 12), Price.build(2, 3), Price.build(0, 91))
        );
    }

}
