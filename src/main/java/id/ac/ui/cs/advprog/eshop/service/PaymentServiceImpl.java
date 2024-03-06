package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status;
        status = switch (method) {
            case "voucherCode" -> paymentByVoucherCode(paymentData);
            case "cashOnDelivery" -> paymentByCOD(paymentData);
            default -> throw new IllegalArgumentException("Invalid payment method");
        };

        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData, status);

        if (status.equals("SUCCESS")) {
            order.setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            order.setStatus("FAILED");
        }

        paymentRepository.savePayment(payment);

        return payment;
    }

    private String paymentByVoucherCode(Map<String, String> paymentData) {
        if (!paymentData.containsKey("voucherCode")) {
            throw new IllegalArgumentException("Voucher code is required");
        }

        if (isValidVoucherCode(paymentData.get("voucherCode"))) {
            return "SUCCESS";
        } else {
            return "REJECTED";
        }
    }

    private String paymentByCOD(Map<String, String> paymentData) {
        if (!paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")) {
            throw new IllegalArgumentException("Address and delivery fee are required for cash on delivery");
        }

        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private boolean isValidVoucherCode(String voucherCode) {
        return voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.chars().filter(Character::isDigit).count() == 8;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Payment setStatus(Payment payment, String status, Order order) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Payment getPayment(String paymentId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Payment> getAllPayments() {
        throw new UnsupportedOperationException("Not implemented");
    }
}