package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        switch (method) {
            case "VOUCHER_CODE":
                handleVoucherCodePayment();
                break;
            case "CASH_ON_DELIVERY":
                handleCashOnDeliveryPayment();
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
    }

    private void handleVoucherCodePayment() {
        String voucherCode = paymentData.get("voucherCode");

        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
            int numCharCount = 0;
            for (int i = 0; i < voucherCode.length(); i++) {
                if (Character.isDigit(voucherCode.charAt(i))) {
                    numCharCount++;
                }
            }
            if (numCharCount == 8) {
                this.status = "SUCCESS";
                return;
            }
        }
        this.status = "REJECTED";
    }

    private void handleCashOnDeliveryPayment() {
        this.status = "SUCCESS";
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
    }
}
