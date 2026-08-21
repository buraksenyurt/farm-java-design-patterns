package Structural.Flyweight;

import java.math.BigDecimal;

// Flyweight nesnesi (Intrinsic state - içsel state)
// Binlerce sipariş tek ve değişmez bir nesne örneğini paylaşabilir.
public record FinancialInstrument(
        String symbol,
        String companyName,
        String exchange,
        String currency,
        BigDecimal tickSize) {
}