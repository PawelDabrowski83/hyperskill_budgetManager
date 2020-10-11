package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
                Arguments.of(new int[]{10, 19}, new Price(10, 19)),
                Arguments.of(new int[]{0, 99}, new Price(0, 99)),
                Arguments.of(new int[]{10, 0}, new Price(9, 100))
        );
    }
}
