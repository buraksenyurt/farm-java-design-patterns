package Structural.Decorator;

// Component arayüzü
// Asıl servis ve decorator sınıflarının uygulayacağı arayüzdür.
public interface ProductCatalogService {
    String getProductName(String productId);
}
