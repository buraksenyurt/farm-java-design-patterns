package Structural.Flyweight;

import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Bu senaryoda, bir hisse senedi alım satım platformu için binlerce sipariş oluşturuluyor.
    Her sipariş aslında aynı finansal enstrümanları (örneğin, "OPNA", "ANTR", "ORCL") kullanıyor olabilir.
    Flyweight deseni, bu finansal enstrümanların tek bir nesne örneğini paylaşarak bellek kullanımını optimize eder.
    
     - FinancialInstrument: Paylaşılan Flyweight nesnesi, sembol, şirket adı, borsa, para birimi gibi bilgileri içerir.
     - FinancialInstrumentFactory: Flyweight nesnelerini yöneten ve paylaşan sınıf. 
        Verilen bir sembol için mevcut bir nesne varsa onu döndürür, yoksa yeni bir nesne oluşturur.
     - DataLoaderService: Pahalı olan tekrarlı nesne üretimlerinden kaçınmak için kullanılan yardımcı sınıf. 
        Elbette gerçek bir uygulamada bu veriler bir veritabanından veya harici bir hizmetten alınır.
     - Order: Her sipariş benzersiz bir sipariş kimliği, sipariş türü (alım/satım), finansal enstrüman (Flyweight), 
        miktar, fiyat ve zaman damgası içerir.
    - StockMarketDemo: Flyweight desenini gösteren ana sınıf. 
        Bir dizi sembol için rastgele siparişler oluşturur ve toplam sipariş sayısı ile 
        finansal enstrümanlara yapılan toplam arama sayısını yazdırır.
*/
public class StockMarketDemo {
    public static void main(String[] args) {
        DataLoaderService dataLoaderService = new DataLoaderService();
        FinancialInstrumentFactory instrumentFactory = new FinancialInstrumentFactory(dataLoaderService);

        String[] symbols = { "OPNA", "ANTR", "ORCL", "AMZN", "TSLA" };
        List<Order> orders = new ArrayList<>();
        Random random = new Random(24);

        for (int i = 0; i < 1000; i++) {
            String symbol = symbols[random.nextInt(symbols.length)];
            OrderType orderType = random.nextBoolean() ? OrderType.BUY : OrderType.SELL;
            int quantity = random.nextInt(100) + 1;
            java.math.BigDecimal price = new java.math.BigDecimal(random.nextDouble() * 1000).setScale(2,
                    RoundingMode.HALF_UP);
            orders.add(
                    new Order(i, orderType, instrumentFactory.getInstrument(symbol), quantity, price, Instant.now()));
        }

        System.out.println("Total orders: " + orders.size());
        System.out.println("Total instrument lookups: " + dataLoaderService.getLookupCount());
    }
}
