package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    OrderRepository orderRepository;

    Map<String, String> IdHash = new HashMap<>();

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status;
        Order specifiedOrder = orderRepository.findById(order.getId());

        if (specifiedOrder == null){
            throw new NoSuchElementException();
        }

        status = switch (method) {
            case "voucherCode" -> paymentByVoucherCode(paymentData);
            case "cashOnDelivery" -> paymentByCOD(paymentData);
            default -> throw new IllegalArgumentException();
        };

        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData, status);

        switch (status) {
            case "SUCCESS" -> specifiedOrder.setStatus("SUCCESS");
            case "REJECTED" -> specifiedOrder.setStatus("FAILED");
        }

        orderRepository.save(specifiedOrder);
        IdHash.put(payment.getId(), specifiedOrder.getId());
        paymentRepository.savePayment(payment);

        return payment;

    }

    private String paymentByVoucherCode(Map<String, String> paymentData) {
        String voucherStatus;

        if (!paymentData.containsKey("voucherCode")) {
            throw new IllegalArgumentException();
        }

        if (isValidVoucherCode(paymentData.get("voucherCode"))) {
            voucherStatus = "SUCCESS";
        } else {
            voucherStatus = "REJECTED";
        }

        return voucherStatus;
    }

    private String paymentByCOD(Map<String, String> paymentData) {
        if (!paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")) {
            throw new IllegalArgumentException();
        }

        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (isEmptyOrNull(address) || isEmptyOrNull(deliveryFee)) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private boolean isValidVoucherCode(String voucherCode) {
        return voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                getDigitCount(voucherCode) == 8;
    }

    private int getDigitCount(String input) {
        return (int) input.chars().filter(Character::isDigit).count();
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());

        if (existingPayment == null) {
            throw new NoSuchElementException();
        }

        existingPayment.setStatus(status);
        paymentRepository.savePayment(existingPayment);

        String orderId = paymentIdToOrderId().get(payment.getId());
        Order associatedOrder = orderRepository.findById(orderId);

        if ("SUCCESS".equals(status)) {
            associatedOrder.setStatus("SUCCESS");
        } else if ("REJECTED".equals(status)) {
            associatedOrder.setStatus("FAILED");
        }

        orderRepository.save(associatedOrder);
        return existingPayment;
    }

    @Override
    public Payment setStatus(Payment payment, String status, Order order) {
        return null;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {return paymentRepository.findAll();}

    public Map<String,String> paymentIdToOrderId() {return IdHash;}
}
