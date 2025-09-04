package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatTest {

    @Mock
    private Feline feline;

    private Cat cat;

    @BeforeEach
    void setUp() {
        // Инициализация объекта Cat перед каждым тестом
        cat = new Cat(feline);
        // Проверка, что мок Feline не null
        assertNotNull(feline, "Feline mock should not be null");
    }

    // Проверяет, что getSound возвращает "Мяу"
    @Test
    void getSoundReturnsMeow() {
        assertEquals("Мяу", cat.getSound());
    }

    // Проверяет, что getFood возвращает результат от feline.eatMeat()
    @Test
    void getFoodReturnsFoodFromFeline() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);
        assertEquals(expectedFood, cat.getFood());
    }

    // Проверяет, что getFood вызывает feline.eatMeat() один раз
    @Test
    void getFoodCallsEatMeatOnFeline() throws Exception {
        cat.getFood();
        verify(feline, times(1)).eatMeat();
    }

    // Проверяет, что getFood выбрасывает исключение, если feline.eatMeat() бросает исключение
    @Test
    void getFoodThrowsExceptionIfFelineThrows() throws Exception {
        when(feline.eatMeat()).thenThrow(new RuntimeException("Ошибка"));
        Exception exception = assertThrows(Exception.class, () -> cat.getFood());
        assertEquals("Ошибка", exception.getMessage());
    }
}