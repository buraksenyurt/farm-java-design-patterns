package Behavioral.Strategy;

public class ExpressShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShipping(Order order) {
        return 25 + order.getWeightKg() * 1.2;
    }
    
}
