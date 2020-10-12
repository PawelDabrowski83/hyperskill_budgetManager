package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriceExtractorTest {

    @DisplayName("should extractPrice() return int[2]{dollars, cents}")
    @ParameterizedTest
    @MethodSource("extractPriceArgumentsProvider")
    void extractPrice (Price expected, String given){
        assertEquals(expected, PriceExtractor.extractPrice(given));
    }
    private static Stream<Arguments> extractPriceArgumentsProvider(){
        return Stream.of(
                Arguments.of(Price.build(35, 43), "Almond 250gm $35.43"),
                Arguments.of(Price.build(10, 10), "LEGO DUPLO Town Farm Animals $10.10"),
                Arguments.of(Price.build(19, 74), "Sensodyne Pronamel Toothpaste $19.74"),
                Arguments.of(Price.build(8, 54), "Hershey's milk chocolate bars $8.54"),
                Arguments.of(Price.build(8, 61), "Gildan LT $8.61"),
                Arguments.of(Price.build(82, 42), "Total: $82.42"),
                Arguments.of(Price.FREE, "Ala ma $0.00 kota")
        );
    }

    @DisplayName("should extractPrice() throws IllegalArgumentException")
    @ParameterizedTest
    @MethodSource("extractPriceArgumentsProvider2")
    void extractPrice(Exception exptected, String given){
        assertThrows(exptected.getClass(), () -> PriceExtractor.extractPrice(given));
    }
    private static Stream<Arguments> extractPriceArgumentsProvider2(){
        return Stream.of(
                Arguments.of(new IllegalArgumentException(), "Almond 250gm $35.43 $1.00 $0.53"),
                Arguments.of(new IllegalArgumentException(), "Ala ma kota"),
                Arguments.of(new IllegalArgumentException(), "")
        );
    }

    @Test
    public void shouldExtractPriceGivenNullThrowNPE(){
        assertThrows(NullPointerException.class, () -> PriceExtractor.extractPrice(null));
    }
//    @Test
//    public void shouldExtractPriceGivenIncorrectPricesReturnIllegalArgumentException(){
//        // given
//        String multiplePrices = "Almond 250gm $35.43 $1.00 $0.53";
//        String zeroPrices = "Ala ma kota";
//        String empty = "";
//
//        // then
//        assertThrows(IllegalArgumentException.class, () -> PriceExtractor.extractPrice(multiplePrices));
//        assertThrows(IllegalArgumentException.class, () -> PriceExtractor.extractPrice(zeroPrices));
//        assertThrows
//    }

}
