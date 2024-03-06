package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
@Getter
@Setter
public class Order {
    String id;
    List<Product> products;
    long orderTime;
    String author;
    String status;

    // Parameterized constructor
    public Order(String id, List<Product> products, long orderTime, String author) {
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    // Parameterized constructor with status
    public Order(String id, List<Product> products, long orderTime, String author, String status) {
        this(id, products, orderTime, author);
        this.setStatus(status);
    }

    public Order() {

    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
