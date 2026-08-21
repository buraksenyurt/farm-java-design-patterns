package Structural.Flyweight;

// Pahalı olan tekrarlı nesne üretimlerinden kaçınmak için kullanılan yardımcı sınıf.
public final class DataLoaderService {
    public int lookupCount = 0;

    public FinancialInstrument getInstrument(String symbol) {
        lookupCount++;
        // Gerçek uygulamada, bu veriler bir veritabanından veya harici bir hizmetten alınır.
        return switch (symbol) {
            case "OPNA" -> new FinancialInstrument("OPNA", "OpenAI Inc.", "NASDAQ", "USD", new java.math.BigDecimal("0.01"));
            case "ANTR" -> new FinancialInstrument("ANTR", "Anthropic Inc.", "NASDAQ", "USD", new java.math.BigDecimal("0.01"));
            case "ORCL" -> new FinancialInstrument("ORCL", "Oracle Corporation", "NASDAQ", "USD", new java.math.BigDecimal("0.01"));
            case "AMZN" -> new FinancialInstrument("AMZN", "Amazon.com, Inc.", "NASDAQ", "USD", new java.math.BigDecimal("0.01"));
            case "TSLA" -> new FinancialInstrument("TSLA", "Tesla, Inc.", "NASDAQ", "USD", new java.math.BigDecimal("0.01"));
            default -> throw new IllegalArgumentException("Unknown symbol: " + symbol);
        };
    }

    public int getLookupCount() {
        return lookupCount;
    }
}
