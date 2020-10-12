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

    @DisplayName("should addPrice() add two prices")
    @ParameterizedTest
    @MethodSource("addPriceArgumentsProvider")
    void addPrice(Price expected, Price base, Price added){
        assertEquals(expected, base.addPrice(added));
    }
    private static Stream<Arguments> addPriceArgumentsProvider(){
        return Stream.of(
                Arguments.of(Price.FREE, Price.FREE, Price.FREE),
                Arguments.of(Price.build(1, 0), Price.build(0, 50), Price.build(0, 50)),
                Arguments.of(Price.build(10, 12), Price.FREE, Price.build(10, 12))
        );
    }
}
