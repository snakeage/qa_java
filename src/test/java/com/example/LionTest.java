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
public class LionTest {

    @Mock
    private Feline feline;

    @BeforeEach
    void setUp() {
        // Убедимся, что мок инициализирован
        assertNotNull(feline, "Feline mock should not be null");
    }

    @Test
    void constructorWithMaleSetsHasManeTrue() throws Exception {
        Lion lion = new Lion("Самец", feline);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    void constructorWithFemaleSetsHasManeFalse() throws Exception {
        Lion lion = new Lion("Самка", feline);
        assertFalse(lion.doesHaveMane());
    }

    @Test
    void constructorWithInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> new Lion("Invalid", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    void doesHaveManeReturnsCorrectValue() throws Exception {
        Lion maleLion = new Lion("Самец", feline);
        Lion femaleLion = new Lion("Самка", feline);
        assertTrue(maleLion.doesHaveMane());
        assertFalse(femaleLion.doesHaveMane());
    }

    @Test
    void getKittensCallsGetKittensOnFeline() throws Exception {
        Lion lion = new Lion("Самец", feline);
        when(feline.getKittens()).thenReturn(2);
        assertEquals(2, lion.getKittens());
        verify(feline, times(1)).getKittens();
    }

    @Test
    void getFoodCallsGetFoodOnFeline() throws Exception {
        Lion lion = new Lion("Самец", feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        assertEquals(expectedFood, lion.getFood());
        verify(feline, times(1)).getFood("Хищник");
    }

    @Test
    void getFoodThrowsExceptionIfFelineThrows() throws Exception {
        Lion lion = new Lion("Самец", feline);
        when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка"));
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка", exception.getMessage());
    }
}