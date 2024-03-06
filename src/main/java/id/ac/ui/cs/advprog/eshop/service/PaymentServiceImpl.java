package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        Payment payment = new Payment(order.getId(), method, paymentData);
        paymentRepository.savePayment(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status, Order order) {
        payment.setStatus(status);
        updateOrderStatus(order, status);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private void updateOrderStatus(Order order, String status) {
        if (status.equals("SUCCESS")) {
            order.setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            order.setStatus("FAILED");
        }
    }

    private Order getOrder(Payment payment) {
        return new Order();
    }
}
