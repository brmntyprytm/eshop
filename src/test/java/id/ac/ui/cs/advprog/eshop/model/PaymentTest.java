package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testPaymentInitialization() {
        String id = "1";
        String method = "Credit Card";
        String status = "SUCCESS";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "1234567890");

        Payment payment = new Payment(id, method, status, paymentData);

        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(status, payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentStatusChange() {
        String id = "1";
        String method = "Credit Card";
        String initialStatus = "PENDING";
        String updatedStatus = "SUCCESS";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("transactionId", "1234567890");
        Payment payment = new Payment(id, method, initialStatus, paymentData);

        payment.setStatus(updatedStatus);

        assertEquals(updatedStatus, payment.getStatus());
    }

    @Test
    void testPaymentDataModification() {
        String id = "1";
        String method = "Credit Card";
        String status = "SUCCESS";
        Map<String, String> initialPaymentData = new HashMap<>();
        initialPaymentData.put("transactionId", "1234567890");
        Payment payment = new Payment(id, method, status, initialPaymentData);

        Map<String, String> updatedPaymentData = new HashMap<>();
        updatedPaymentData.put("transactionId", "0987654321");
        payment.setPaymentData(updatedPaymentData);

        assertEquals(updatedPaymentData, payment.getPaymentData());
    }
}
