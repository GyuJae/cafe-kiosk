package sample.cafekiosk.unit;

import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CafeKiosk {
    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        this.beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음료는 1잔 이상 주문하실 수 있습니다.");
        }

        for (int i = 0; i < count; i++) {
            this.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        this.beverages.remove(beverage);
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : this.beverages) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), this.beverages);
    }

    public List<Beverage> getBeverages() {
        return this.beverages;
    }

    public void clear() {
        this.beverages.clear();
    }
}
