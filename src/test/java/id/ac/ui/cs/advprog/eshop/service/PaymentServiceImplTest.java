package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void testAddPaymentSuccess() {
        Order order = new Order();
        String method = "voucherCode";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABCD");

        Payment payment = new Payment("1", method, paymentData, "SUCCESS");

        Payment addedPayment = paymentService.addPayment(order, method, paymentData);

        assertEquals(payment, addedPayment);
    }

    @Test
    void testAddPaymentRejected() {
        Order order = new Order();
        String method = "cashOnDelivery";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "10.00");

        Payment payment = new Payment("2", method, paymentData, "REJECTED");

        Payment addedPayment = paymentService.addPayment(order, method, paymentData);

        assertEquals(payment, addedPayment);
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment("1", "voucherCode", new HashMap<>(), "SUCCESS");
        Order order = new Order();

        Payment updatedPayment = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", updatedPayment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment("2", "cashOnDelivery", new HashMap<>(), "REJECTED");
        Order order = new Order();

        Payment updatedPayment = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", updatedPayment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testGetPayment() {
        Payment payment = new Payment("3", "voucherCode", new HashMap<>(), "SUCCESS");
        when(paymentRepository.findById("3")).thenReturn(payment);

        Payment foundPayment = paymentService.getPayment("3");

        assertEquals(payment, foundPayment);
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = new Payment("4", "voucherCode", new HashMap<>(), "SUCCESS");
        Payment payment2 = new Payment("5", "cashOnDelivery", new HashMap<>(), "REJECTED");
        when(paymentRepository.findAll()).thenReturn(List.of(payment1, payment2));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }
}
