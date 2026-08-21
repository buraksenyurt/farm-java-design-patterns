package Structural.Decorator;

// Decorator sınıfı
// Asıl servis sınıfını sarmalayan ve ek davranışlar ekleyen soyut sınıf olarak düşünebiliriz
// Bu nedenle ProductCatalogService sınıfını doğrudan extend etmiyoruz, abstract bir decorator kullanıyoruz.
public abstract class ProductCatalogServiceDecorator implements ProductCatalogService {
    protected final ProductCatalogService productCatalogService;

    // ProductCatalogService nesnemizi constructor üzerinden enjekte ediyoruz.
    // Bu sayede asıl servis sınıfını sarmalayabiliyoruz.
    protected ProductCatalogServiceDecorator(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @Override
    public String getProductName(String productId) {
        return productCatalogService.getProductName(productId);
    }

}
