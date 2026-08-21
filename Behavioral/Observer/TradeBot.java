package Behavioral.Observer;

public class TradeBot implements Observer {
    private double lastPrice;

    public TradeBot(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Override
    public void update(String symbol, double price) {

        if (price > lastPrice) {
            System.out.println("TradeBot: " + symbol + " fiyatı yükseldi, alım yapılıyor: " + price);
        } else if (price < lastPrice) {
            System.out.println("TradeBot: " + symbol + " fiyatı düştü, satış yapılıyor: " + price);
        } else {
            System.out.println("TradeBot: " + symbol + " fiyatı değişmedi: " + price);
        }
        lastPrice = price;
    }
}
