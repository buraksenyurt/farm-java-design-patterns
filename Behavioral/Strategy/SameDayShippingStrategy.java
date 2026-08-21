package Behavioral.Strategy;

public class SameDayShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(Order order) {
        return 50 + order.getWeightKg() * 2.0;
    }
    
}
