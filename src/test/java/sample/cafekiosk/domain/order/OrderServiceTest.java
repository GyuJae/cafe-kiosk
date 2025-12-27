package sample.cafekiosk.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.domain.order.request.OrderCreateRequest;
import sample.cafekiosk.domain.order.response.OrderResponse;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductRepository;
import sample.cafekiosk.domain.product.ProductSellingStatus;
import sample.cafekiosk.domain.product.ProductType;
import sample.cafekiosk.domain.product.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        // Given
        LocalDateTime registeredDateTime = LocalDateTime.now();

        Product product1 = createProduct(ProductType.HANDMADE, "001", 1000);
        Product product2 = createProduct(ProductType.HANDMADE, "002", 3000);
        Product product3 = createProduct(ProductType.HANDMADE, "003", 5000);
        this.productRepository.saveAll(List.of(product1, product2, product3));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();

        // When
        OrderResponse orderResponse = this.orderService.createOrder(request, registeredDateTime);

        // Then
        assertNotNull(orderResponse.getId());
        assertEquals(4000, orderResponse.getTotalPrice());
        assertEquals(registeredDateTime, orderResponse.getRegisteredDateTime());
        assertEquals(2, orderResponse.getProducts().size());
        assertArrayEquals(List.of("001", "002").toArray(), orderResponse.getProducts().stream().map(ProductResponse::getProductNumber).toArray());

    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .build();
    }
}