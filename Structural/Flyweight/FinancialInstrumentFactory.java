package Structural.Flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Flyweight Factory - Flyweight nesnelerini yöneten ve paylaşan sınıf.
public final class FinancialInstrumentFactory {

    private final Map<String, FinancialInstrument> cache = new ConcurrentHashMap<>();
    private final DataLoaderService dataLoaderService;

    public FinancialInstrumentFactory(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    public FinancialInstrument getInstrument(String symbol) {
        // computeIfAbsent, sembol zaten varsa mevcut nesneyi döndürür, yoksa yeni bir nesne oluşturur ve önbelleğe ekler.
        // Concurrent çağrı yapan istemciler için thread-safe bir şekilde çalışır yani data-race olmaz.
        return cache.computeIfAbsent(symbol, dataLoaderService::getInstrument);
    }

    public int getCacheSize() {
        return cache.size();
    }
}
