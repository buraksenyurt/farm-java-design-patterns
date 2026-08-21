package Structural.Decorator;

// Bu da bir diğer Concrete Decorator sınıfı.
// Sembolik olarak bir rate limiter ekliyoruz. 
// Yani, belirli bir süre içerisinde belirli sayıda istek yapılmasını istediğimiz durumlar için kullanabiliriz.
public class RateLimiterProductCatalogServiceDecorator extends ProductCatalogServiceDecorator {

    private final int maxRequests;
    private int requestCount;

    public RateLimiterProductCatalogServiceDecorator(ProductCatalogService productCatalogService, int maxRequests) {
        super(productCatalogService);
        this.maxRequests = maxRequests;
        this.requestCount = 0;
    }

    @Override
    public String getProductName(String productId) {
        if (requestCount >= maxRequests) {
            throw new RuntimeException("Rate limit exceeded. Please try again later.");
        }
        requestCount++;
        return super.getProductName(productId);
    }
    
}
