package Behavioral.Observer;

public class PriceAlert implements Observer {
    private double alertPrice;

    public PriceAlert(double alertPrice) {
        this.alertPrice = alertPrice;
    }

    @Override
    public void update(String symbol, double price) {
        if (price >= alertPrice) {
            System.out.println("PriceAlert: " + symbol + " fiyatı " + price + " seviyesine ulaştı!");
        }
    }
    
}
