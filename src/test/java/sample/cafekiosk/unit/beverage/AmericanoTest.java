package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    @DisplayName("아메리카노의 이름을 보여줍니다.")
    void getName() {
        Americano americano = new Americano();

        assertEquals("아메리카노", americano.getName());
    }

    @Test
    @DisplayName("아메리카노의 가격을 보여줍니다.")
    void getPrice() {
        Americano americano = new Americano();

        assertEquals(4000, americano.getPrice());
    }
}