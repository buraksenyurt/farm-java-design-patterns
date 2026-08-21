package Behavioral.Strategy;

/*
    En sık kullandığımız tasarım desenelerinden birisi strateji tasarım desenidir. 
    Bir sipariş için kargo ücreti hesaplaması yapıldığını düşünelim.
    Standart, Express ve Aynı Gün gibi kargo seçenekleri olsun. Herbiri için farklı fiyatlandırmalar söz konusudur.
    Kuvvetle muhtemel business içerisinde bunu aşağıdaki gibi bir if else yapısı ile çözeriz.

    double calculateShipping(Order order, String method) {
        if (method.equals("STANDARD")) {
            return 10 + order.getWeightKg() * 0.5;
        } else if (method.equals("EXPRESS")) {
            return 25 + order.getWeightKg() * 1.2;
        } else if (method.equals("SAME_DAY")) {
            return 50 + order.getWeightKg() * 2.0;
        }
        throw new IllegalArgumentException("Unknown method");
    }

    Burada problem şudur; yeni bir kargo yöntemi eklemek istediğimizde mevcut kodu değiştirme zorunda kalırız.
    Kabaca yeni bir if bloğu ekleriz. Burada Open/Closed prensibi de ihlal edilmiş olur. Bu gibi bir durumda,
    strateji tasarım deseni ile SOLID'e bağlı kalarak bir çözüm üretebiliriz.

    Bu örnekte söz konusu desen ele alınmaktadır. Kodu tamamladıktan sonra International taşıma seçeneğini
    ekleyelim ve nereleri değiştirip hangi ilkelere bağlı kaldığımızı tartışalım.
*/
public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        order.setWeightKg(5.0);
        order.setDestinationCity("New York");
        order.setFragile(true);

        ShippingStrategy standardStrategy = new StandardShippingStrategy();
        ShippingStrategy expressStrategy = new ExpressShippingStrategy();
        ShippingStrategy sameDayStrategy = new SameDayShippingStrategy();

        ShippingCostCalculator calculator;

        // Standart gönderim
        calculator = new ShippingCostCalculator(standardStrategy);
        System.out.println("Standard Shipping Cost: " + calculator.calculateShipping(order));

        // Express gönderim
        calculator = new ShippingCostCalculator(expressStrategy);
        System.out.println("Express Shipping Cost: " + calculator.calculateShipping(order));

        // Aynı gün gönderim
        calculator = new ShippingCostCalculator(sameDayStrategy);
        System.out.println("Same Day Shipping Cost: " + calculator.calculateShipping(order));
    }
}
