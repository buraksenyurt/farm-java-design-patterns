package Behavioral.Observer;

/*
    Observer Design Pattern

    Şöyle bir senaryo düşünelim: Sistemde, stok ürünlerindeki fiyat değişimlerine göre haberdar edilmesi gereken bazı enstrümanlar var.
    Örneğin arayüz üzerindeki bir dashboard (Display Widget), basit bir ticari bot ve alarm mekanizası(threshold alarm) gibi. 
    Bunun yönetimini pekala aşağıdaki gibi tek bir bileşene üzerinden yönetmeyi de düşünebiliriz.

    class StockTicker {
        private PriceDisplay display = new PriceDisplay();
        private PriceAlert alert = new PriceAlert(150.0);
        private TradingBot bot = new TradingBot();

        void setPrice(double price) {
            this.price = price;
            display.refresh(price);
            alert.checkThreshold(price);
            bot.evaluate(price);
        }
    }

    Burada iki önemli problem vardır; StockTicker sınıfı asıl işi yapan somut bileşenlere sıkı sıkıya bağlıdır(Tight Coupling). 
    Eğer yeni bir bileşen eklemek istersek, StockTicker sınıfını değiştirmek zorunda kalırız. 
    Bu da OCP( Open/Closed Principle) ilkesine aykırıdır.

    Hatırlanacağı üzere Strategy Design Pattern'de buna benzer bir problem var. 
    Ancak Strategy Design Pattern'de cevaplanan şey hangi algoritmanın kullanılacağıdır.
    Observer Design Pattern'de ise cevaplanan şey hangi bileşenlerin haberdar edileceğidir.

    Bu örnekte Observer Design Pattern'i kullanarak StockTicker sınıfı yeniden tasarlanmaktadır.
*/

public class Main {
    public static void main(String[] args) {
        StockTicker stockTicker = new StockTicker();

        // Gözlemcileri kaydet
        PriceDashboard dashboard = new PriceDashboard();
        stockTicker.registerObserver(dashboard);
        stockTicker.registerObserver(new PriceAlert(150.0));
        stockTicker.registerObserver(new TradeBot(100.0));

        // Fiyat değişikliklerini simüle et
        stockTicker.setPrice("GLSS", 120.0);
        stockTicker.setPrice("GLSS", 160.0);
        stockTicker.setPrice("GLSS", 90.0);

        /*
         * Observer Design Pattern'in Strategy'den bir farkı daha; kayıtlı gözlemci
         * listesi çalışma zamanında (runtime) değiştirilebilir. Burada dashboard'u
         * çıkarıyoruz; sonraki bildirimde artık PriceDashboard çıktısı görülmeyecek,
         * diğer gözlemciler haberdar olmaya devam edecek.
         */
        stockTicker.removeObserver(dashboard);
        System.out.println("\t\nPriceDashboard aboneliği iptal edildi.\n");
        stockTicker.setPrice("GLSS", 200.0);
    }
}