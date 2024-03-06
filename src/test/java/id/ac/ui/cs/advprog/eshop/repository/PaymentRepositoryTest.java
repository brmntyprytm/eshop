package id.ac.ui.cs.advprog.eshop.repository.;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Payment payment = createSamplePayment("1", "Credit Card", "SUCCESS");

        paymentRepository.savePayment(payment);

        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByIdExistingPayment() {
        Payment payment = createSamplePayment("1", "Credit Card", "SUCCESS");
        paymentRepository.savePayment(payment);

        Payment foundPayment = paymentRepository.findById("1");

        assertNotNull(foundPayment);
        assertEquals(payment, foundPayment);
    }

    @Test
    void testFindByIdNonExistingPayment() {
        Payment payment = createSamplePayment("1", "Credit Card", "SUCCESS");
        paymentRepository.savePayment(payment);

        Payment foundPayment = paymentRepository.findById("2");

        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        Payment payment1 = createSamplePayment("1", "Credit Card", "SUCCESS");
        Payment payment2 = createSamplePayment("2", "Cash", "REJECTED");
        paymentRepository.savePayment(payment1);
        paymentRepository.savePayment(payment2);

        int paymentCount = paymentRepository.findAll().size();

        assertEquals(2, paymentCount);
    }

    private Payment createSamplePayment(String id, String method, String status) {
        Map<String, String> paymentData = new HashMap<>();
        return new Payment(id, method, status, paymentData);
    }
}
