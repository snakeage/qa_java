package com.example;
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

    @Test
    void getSoundReturnsMeow() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodCallsEatMeatOnFeline() throws Exception {
        Cat cat = new Cat(feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);
        assertEquals(expectedFood, cat.getFood());
        verify(feline, times(1)).eatMeat();
    }

    @Test
    void getFoodThrowsExceptionIfFelineThrows() throws Exception {
        Cat cat = new Cat(feline);
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка"));
        Exception exception = assertThrows(Exception.class, cat::getFood);
        assertEquals("Ошибка", exception.getMessage());
    }
}
