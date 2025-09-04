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

    private Lion lion;

    @BeforeEach
    void setUp() throws Exception {
        // Инициализация объекта Lion с полом "Самец" перед каждым тестом
        lion = new Lion("Самец", feline);
        // Проверка, что мок Feline не null
        assertNotNull(feline, "Feline mock should not be null");
    }

    // Проверяет, что для пола "Самец" hasMane устанавливается в true
    @Test
    void constructorWithMaleSetsHasManeTrue() {
        assertTrue(lion.doesHaveMane());
    }

    // Проверяет, что для пола "Самка" hasMane устанавливается в false
    @Test
    void constructorWithFemaleSetsHasManeFalse() throws Exception {
        Lion femaleLion = new Lion("Самка", feline);
        assertFalse(femaleLion.doesHaveMane());
    }

    // Проверяет, что конструктор выбрасывает исключение для неверного пола
    @Test
    void constructorWithInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> new Lion("Invalid", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    // Проверяет, что doesHaveMane возвращает true для самца
    @Test
    void doesHaveManeReturnsTrueForMale() {
        assertTrue(lion.doesHaveMane());
    }

    // Проверяет, что doesHaveMane возвращает false для самки
    @Test
    void doesHaveManeReturnsFalseForFemale() throws Exception {
        Lion femaleLion = new Lion("Самка", feline);
        assertFalse(femaleLion.doesHaveMane());
    }

    // Проверяет, что getKittens возвращает результат от feline.getKittens()
    @Test
    void getKittensReturnsValueFromFeline() throws Exception {
        when(feline.getKittens()).thenReturn(2);
        assertEquals(2, lion.getKittens());
    }

    // Проверяет, что getKittens вызывает feline.getKittens() один раз
    @Test
    void getKittensCallsGetKittensOnFeline() throws Exception {
        lion.getKittens();
        verify(feline, times(1)).getKittens();
    }

    // Проверяет, что getFood возвращает результат от feline.getFood("Хищник")
    @Test
    void getFoodReturnsFoodFromFeline() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        assertEquals(expectedFood, lion.getFood());
    }

    // Проверяет, что getFood вызывает feline.getFood("Хищник") один раз
    @Test
    void getFoodCallsGetFoodOnFeline() throws Exception {
        lion.getFood();
        verify(feline, times(1)).getFood("Хищник");
    }

    // Проверяет, что getFood выбрасывает исключение, если feline.getFood бросает исключение
    @Test
    void getFoodThrowsExceptionIfFelineThrows() throws Exception {
        when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка"));
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка", exception.getMessage());
    }
}