package enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;
    private OrderStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (OrderStatus orderstatus : OrderStatus.values()) {
            if (orderstatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
