package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private List<Payment> payments;

    public PaymentRepository() {
        this.payments = new ArrayList<>();
    }

    public void savePayment(Payment payment) {
        payments.add(payment);
    }

    public Payment findById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }
}
