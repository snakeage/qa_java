package com.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LionTest {
    @Mock
    private Feline feline;

    @ParameterizedTest
        @ValueSource(strings = {"Самец"})
        void constructorMaleSetsHasManeTrue(String sex) throws Exception {
            Lion lion = new Lion(sex, feline);
            assertTrue(lion.hasMane);
    }
}
