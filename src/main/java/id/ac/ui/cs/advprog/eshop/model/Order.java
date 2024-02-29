package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

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
    }

    // Parameterized constructor with status
    public Order(String id, List<Product> products, long orderTime, String author, String status) {
    }
}
