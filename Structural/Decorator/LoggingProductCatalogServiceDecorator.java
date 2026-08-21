package Structural.Decorator;

// Concrete Decorator sınıflarımızdan birisi.
// Logging işlemini getProductName davranışına eklemek için kullanılmakta.
public class LoggingProductCatalogServiceDecorator extends ProductCatalogServiceDecorator {

    public LoggingProductCatalogServiceDecorator(ProductCatalogService productCatalogService) {
        super(productCatalogService);
    }

    @Override
    public String getProductName(String productId) {
        System.out.println("Logging: Getting product name for productId: " + productId);
        String productName = super.getProductName(productId);
        System.out.println("Logging: Retrieved product name: " + productName);
        return productName;
    }
    
}
