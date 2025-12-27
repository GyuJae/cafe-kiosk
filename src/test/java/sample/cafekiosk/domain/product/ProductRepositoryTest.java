package sample.cafekiosk.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상품을 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // Given
        Product product1 = Product.builder()
                .productNumber("001")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.HOLD)
                .name("카페라떼")
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.STOP_SELLING)
                .name("팥빙수")
                .price(7000)
                .build();
        this.productRepository.saveAll(List.of(product1, product2, product3));

        // When
        List<Product> products = this.productRepository.findAllBySellingStatusIn(List.of(ProductSellingStatus.SELLING, ProductSellingStatus.HOLD));

        // Then
        assertEquals(2, products.size());
        assertArrayEquals(List.of(product1, product2).toArray(), products.toArray());
    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // Given
        Product product1 = Product.builder()
                .productNumber("001")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.HOLD)
                .name("카페라떼")
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.STOP_SELLING)
                .name("팥빙수")
                .price(7000)
                .build();
        this.productRepository.saveAll(List.of(product1, product2, product3));

        // When
        List<Product> products = this.productRepository.findAllByProductNumberIn(List.of("001", "002"));

        // Then
        assertEquals(2, products.size());
        assertArrayEquals(List.of(product1, product2).toArray(), products.toArray());
    }
}