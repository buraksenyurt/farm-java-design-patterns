package Structural.Decorator;

/*
    Senaryo:

    Bir ürün kataloğu servisimiz var ve getProductName(productId) ile veritabanından
    ürün adını alıyoruz. Bu servise ek olarak üç farklı davranış eklemek istiyoruz:

      1) Logging: her çağrının ne zaman yapıldığını ve sonucunu loglamak,
      2) Caching: aynı productId için tekrar veritabanına gitmemek,
      3) Rate Limiting: belirli bir sürede en fazla N adet yeni yani cache'te olmayan
         veritabanı sorgusuna izin vermek (limit aşılırsa istek reddedilmeli)

    Bu davranışları doğrudan DatabaseProductCatalogService sınıfının içine gömersek, sınıf
    hem "ürünü nasıl getiririm?" hem de "nasıl loglarım/cache'lerim/limitlerim?"
    sorumluluklarını taşımış olur(BU açık bir Single Responsibility ihlali). 
    
    Üstelik bu davranışlardan sadece bir kısmını kullanmak istediğimizde(örneğin caching olmadan sadece logging), 
    türetme(inheritance) ile her kombinasyon için ayrı bir alt sınıf yazmamız gerekirdi(class explosion).

    Çözüm:

    Her davranışı, ProductCatalogService arayüzünü uygulayan bağımsız birer Decorator
    sınıfı olarak yazacağız (LoggingProductCatalogServiceDecorator,
    CachingProductCatalogServiceDecorator, RateLimiterProductCatalogServiceDecorator gibi).
    Object user bunları çalışma zamanında(runtime) sarmalayarak(compose)
    ihtiyaç duyduğu kombinasyonu oluşturacak. Ne DatabaseProductCatalogService ne de diğer
    decorator sınıfları birbirlerinin ya da sarmalama işleminin varlığından haberdar olacaklar.

    Burada sarmalama sırasına da dikkat etmek lazım zira davranışı doğrudan etkiler.
    Caching, RateLimiter'ın dışında olduğu için ( Logging( Caching( RateLimiter( Database ) ) ) ), 
    bir cache hit RateLimiter'a hiç uğramaz. Yani rate limit yalnızca gerçekten veritabanına giden
    (cache'te olmayan) isteklere uygulanır. Gerçek bir sistemde de tercih edeceğimiz
    mantıklı sıralama da budur zira zaten önbellekten dönen bir isteği limitlemenin bir anlamı yoktur.
*/
public class Main {
    public static void main(String[] args) {
        ProductCatalogService productCatalogService = new LoggingProductCatalogServiceDecorator(
                new CachingProductCatalogServiceDecorator(
                        new RateLimiterProductCatalogServiceDecorator(
                                new DatabaseProductCatalogService(), 3)));

        // Örneğimizde maxRequests = 3 olduğu için, cache'te olmayan en fazla 3
        // productId veritabanına ulaşabilir. Burada bilerek 3'ten fazla farklı
        // productId deniyoruz ki RateLimiter'ın gerçekten devreye girdiğini görelim.
        String[] productIds = {
                "100045", // 1nci farklı id -> cache miss, DB'ye gider (requestCount: 0 -> 1)
                "100046", // 2nci farklı id -> cache miss, DB'ye gider (requestCount: 1 -> 2)
                "100047", // 3ncü farklı id -> cache miss, DB'ye gider (requestCount: 2 -> 3)
                "100048", // 4üncü farklı id -> limit dolu, RateLimiter isteği reddeder
                "100045", // daha önce cache'lendi -> RateLimiter'a hiç uğramadan cache'ten döner
                "100049" // yine yeni bir id var -> limit hala dolu, yine reddedilir
        };

        for (String productId : productIds) {
            try {
                String productName = productCatalogService.getProductName(productId);
                System.out.println("Sonuç: " + productName);
            } catch (RuntimeException e) {
                System.out.println("İstek reddedildi (" + productId + "): " + e.getMessage());
            }
            System.out.println("---");
        }
    }
}