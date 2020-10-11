package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PriceExtractorTest {

    @DisplayName("should extractPrice() return int[2]{dollars, cents}")
    @ParameterizedTest
    @MethodSource("extractPriceArgumentsProvider")
    void extractPrice (int[] expected, String given){
        assertArrayEquals(expected, PriceExtractor.extractPrice(given));
    }
    private static Stream<Arguments> extractPriceArgumentsProvider(){
        return Stream.of(
                Arguments.of(new int[]{35, 43}, "Almond 250gm $35.43"),
                Arguments.of(new int[]{10, 10}, "LEGO DUPLO Town Farm Animals $10.10"),
                Arguments.of(new int[]{19, 74}, "Sensodyne Pronamel Toothpaste $19.74"),
                Arguments.of(new int[]{})
        );
    }
}
