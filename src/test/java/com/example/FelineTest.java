package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FelineTest {
    private final Feline feline = new Feline();

    @Test
    void getFamily_returnsFelineFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void getKittens_withoutParameters_returnsOne() {
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest
        @ValueSource(ints = {0, 1, 5})
        void getKittens_withParameters_returnsInputValue(int kittensCount) {
            assertEquals(kittensCount, feline.getKittens(kittensCount));
    }

    @Test
    void eatMeat_returnsPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    void getFood_predator_returnsCorrectFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.getFood("Хищник"));
    }

    @Test
    void getFood_herbivore_returnsCorrectFood() throws Exception {
        List<String> expectedFood = List.of("Трава", "Различные растения");
        assertEquals(expectedFood, feline.getFood("Травоядное"));
    }

    @Test
    void getFood_unknownKind_throwsException() {
        Exception exception = assertThrows(Exception.class, () -> feline.getFood("Неизвестный"));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }
}
