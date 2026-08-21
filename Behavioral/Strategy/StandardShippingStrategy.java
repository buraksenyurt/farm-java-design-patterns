package Behavioral.Strategy;

public class StandardShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(Order order) {
        return 10 + order.getWeightKg() * 0.5;
    }
    
}
