package Structural.Decorator;

// Concrete Component sınıfı
public class DatabaseProductCatalogService implements ProductCatalogService {

    @Override
    public String getProductName(String productId) {
        // Burada veritabanına gidip geldiğimiz bir operasyon var.
        // Dramatize etmek için Thread'i bir süre uyutalım.
        try {
            Thread.sleep(1000); // 1 saniye uyutuyoruz.
            System.out.println("Database query executed for productId: " + productId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "PRD: " + productId;
    }

}
