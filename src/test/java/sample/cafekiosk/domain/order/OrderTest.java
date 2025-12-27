package sample.cafekiosk.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductSellingStatus;
import sample.cafekiosk.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // Given
        List<Product> products = List.of(this.createProduct("001", 1000), this.createProduct("002", 2000));

        // When
        Order order = Order.create(products, LocalDateTime.now());

        // Then
        assertEquals(3000, order.getTotalPrice());
    }

    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    @Test
    void initStatus() {
        // Given
        List<Product> products = List.of(this.createProduct("001", 1000), this.createProduct("002", 2000));

        // When
        Order order = Order.create(products, LocalDateTime.now());

        // Then
        assertEquals(OrderStatus.INIT, order.getOrderStatus());
    }

    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    @Test
    void registeredDateTime() {
        // Given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        List<Product> products = List.of(this.createProduct("001", 1000), this.createProduct("002", 2000));

        // When
        Order order = Order.create(products, registeredDateTime);

        // Then
        assertEquals(registeredDateTime, order.getRegisteredDateTime());
    }


    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(ProductType.HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .build();
    }
}