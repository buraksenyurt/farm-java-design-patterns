package Structural.Decorator;

import java.util.Map;
import java.util.HashMap;

// Concrete Decorator sınıflarımızdan birisi.
// Caching işlemini getProductName davranışına eklemek için kullanılıyor diyebiliriz.
public class CachingProductCatalogServiceDecorator extends ProductCatalogServiceDecorator {

    private final Map<String, String> cache = new HashMap<>();

    public CachingProductCatalogServiceDecorator(ProductCatalogService productCatalogService) {
        super(productCatalogService);
    }

    @Override
    public String getProductName(String productId) {
        if (cache.containsKey(productId)) {
            System.out.println("Cache hit for productId: " + productId);
            return cache.get(productId);
        } else {
            System.out.println("Cache miss for productId: " + productId);
            String productName = super.getProductName(productId);
            cache.put(productId, productName);
            return productName;
        }
    }

}
