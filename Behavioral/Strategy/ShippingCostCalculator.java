package Behavioral.Strategy;

// Context sınıfı. Gönderim maliyetini hesaplamak için kullanılan stratejiyi temsil eder.
// Yeni bir strateji eklendiğinde veya mevcut bir strateji değiştirildiğinde, bu sınıfın kodunu değiştirmeye gerek yoktur.
public class ShippingCostCalculator{
    private ShippingStrategy shippingStrategy;

    public ShippingCostCalculator(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateShipping(Order order) {
        return shippingStrategy.calculateShipping(order);
    }
    
}
