package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class PaymentTest {
    private Order order;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        this.order = new Order();
        this.paymentRepository = new PaymentRepository();
    }

    @Test
    void testAddPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "123456789");
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.addPayment(order, "Credit Card", paymentData);

        assertNotNull(payment);
        assertEquals("Credit Card", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertTrue(paymentRepository.getAllPayments().contains(payment));
    }

    @Test
    void testSetStatus_Success() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "123456789");
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.addPayment(order, "Credit Card", paymentData);

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testSetStatus_Rejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "123456789");
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.addPayment(order, "Credit Card", paymentData);

        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testGetPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "123456789");
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.addPayment(order, "Credit Card", paymentData);

        Payment retrievedPayment = paymentService.getPayment(payment.getId());

        assertEquals(payment, retrievedPayment);
    }

    @Test
    void testGetAllPayments() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("transactionId", "123456789");
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("transactionId", "987654321");
        PaymentService paymentService = new PaymentService();
        Payment payment1 = paymentService.addPayment(order, "Credit Card", paymentData1);
        Payment payment2 = paymentService.addPayment(order, "PayPal", paymentData2);

        assertTrue(paymentService.getAllPayments().contains(payment1));
        assertTrue(paymentService.getAllPayments().contains(payment2));
    }
}
