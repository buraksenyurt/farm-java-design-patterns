package Creational.Prototype;

/*
    Klonlama(cloning) maliyeti yüksek olan nesne üretimlerinde Prototype Design Pattern kullanılabilir.

    Klasik bir senaryo ile gidelim. Üretim maliyeti yüksek bir rapor nesnesini ele alalım. Gerçek bir sistemi göz 
    önüne aldığımızda bu rapor hazırlanırken varsayılan bölümler, stiller, tablo şablonları ve hatta veritabanı 
    round-trip (veritabanına bağlanıp veri çekme) işlemleri her seferinde yapılıyor olabilir. 

    Sistemin satış, finansman, pazarlama gibi farklı departmanlara ait raporlarında neredeyse aynı ortak 
    gereksinimler kullanılıyor olabilir. Bunu aşağıdaki gibi çözmeye çalışabiliriz.

    Report salesReport = new Report("Genel");     // setup maliyeti yüksek, varsayılanlar hazırlanıyor
    salesReport.setTitle("Sales Report");

    Report financeReport = new Report("Genel");   // aynı varsayılanlar tekrar hazırlanıyor, maliyet yüksek
    financeReport.setTitle("Finance Report");

    Bu örnek projede bu vakayı basitçe Prototype Design Pattern ile çözmeye çalışacağız. Hatta GoF (Gang of Four) aksine
    daha idiomatik bir çözüm ile ilerleyeceğiz.
*/

public class Main {
    public static void main(String[] args) {

        // Prototype yalnızca bir defa maliyetli constructor üzerinden oluşturulur.
        // Aşağıda "Maliyet yüksek" mesajının çıktıda sadece bir defa görüneceğine
        // dikkat etmek lazım. copy() çağrıları bu maliyetli kurulumu bir daha
        // çalıştırmaz.
        Report basePrototype = new Report("Base");

        Report salesReport = basePrototype.copy();
        salesReport.setTitle("Sales Report");
        salesReport.setDepartment("Sales");
        salesReport.addSection("Sales Summary");
        salesReport.addSection("Sales Charts");

        Report financeReport = basePrototype.copy();
        financeReport.setTitle("Finance Report");
        financeReport.setDepartment("Finance");
        financeReport.addSection("Finance Summary");
        financeReport.addSection("Finance Charts");
        financeReport.addSection("Finance Notes");

        // basePrototype'ı da yazdırıyoruz ki deep copy'nin gerçekten işe yaradığını
        // görelim. salesReport ve financeReport'a eklenen bölümler, prototype'ın kendi
        // sections listesine (Title, Summary, Charts, Notes) hiçbir şekilde sızmamış
        // olmalılar.
        System.out.println("Base Prototype: " + basePrototype);
        System.out.println("Sales Report: " + salesReport);
        System.out.println("Finance Report: " + financeReport);
    }
}
