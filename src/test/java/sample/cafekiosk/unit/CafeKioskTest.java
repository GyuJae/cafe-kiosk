package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {
    @Test
    @DisplayName("주문서에 음료를 추가할 수 있습니다.")
    void add() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();

        // When
        cafeKiosk.add(new Americano());

        // Then
        assertEquals(1, cafeKiosk.getBeverages().size());
        assertEquals("아메리카노", cafeKiosk.getBeverages().get(0).getName());
    }

    @Test
    @DisplayName("주문서에 한 종류의 음료를 여러개 추가할 수 있습니다.")
    void addSeveralBeverages() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // When
        cafeKiosk.add(americano, 2);

        // Then
        assertEquals(americano, cafeKiosk.getBeverages().get(0));
        assertEquals(americano, cafeKiosk.getBeverages().get(1));
    }

    @Test
    @DisplayName("주문서에 음료는 1개 이상 추가할 수 있습니다.")
    void addZeroBeverages() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> cafeKiosk.add(americano, 0));
    }

    @Test
    @DisplayName("주문서에서 추가된 음료를 제거할 수 있습니다.")
    void remove() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        // When
        cafeKiosk.remove(americano);

        // Then
        assertTrue(cafeKiosk.getBeverages().isEmpty());
    }

    @Test
    @DisplayName("주문서에서 모든 음료를 제거할 수 있습니다.")
    void clear() {
        // Given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte lette = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(lette);

        // When
        cafeKiosk.clear();

        // Then
        assertTrue(cafeKiosk.getBeverages().isEmpty());
    }
}