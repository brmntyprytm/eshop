package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    private ProductService productService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        productService = new ProductServiceImpl(paymentRepository);
    }

    @Test
    void testAddPayment() {
        Order order = new Order();
        String method = "Credit Card";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "1234567890");
        Payment payment = new Payment("1", method, paymentData);
        when(paymentRepository.savePayment(payment)).thenReturn(payment);

        Payment addedPayment = productService.addPayment(order, method, paymentData);

        assertEquals(payment, addedPayment);
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment("1", "Credit Card", new HashMap<>());
        Order order = new Order();
        payment.setOrder(order);

        Payment updatedPayment = productService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", updatedPayment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment("1", "Credit Card", new HashMap<>());
        Order order = new Order();
        payment.setOrder(order);

        Payment updatedPayment = productService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", updatedPayment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testGetPayment() {
        Payment payment = new Payment("1", "Credit Card", new HashMap<>());
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment foundPayment = productService.getPayment("1");

        assertEquals(payment, foundPayment);
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = new Payment("1", "Credit Card", new HashMap<>());
        Payment payment2 = new Payment("2", "Cash", new HashMap<>());
        when(paymentRepository.findAll()).thenReturn(List.of(payment1, payment2));

        var payments = productService.getAllPayments();

        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }
}
