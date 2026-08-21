package Structural.Flyweight;

import java.math.BigDecimal;
import java.time.Instant;

public record Order(
        long orderId,
        OrderType orderType,
        FinancialInstrument instrument, // Paylaşılan Flyweight nesnesi olmalıdır
        int quantity,
        BigDecimal price,
        Instant timestamp) {
    public BigDecimal notional() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
