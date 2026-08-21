package Creational.PrototypeGoFStyle;

/*
    Bu örnekte ise Creational/Prototype paketindeki ile aynı senaryoyu (maliyeti yüksek Report
    üretimi) klasik GoF(Gang of Four) yaklaşımıyla, yani Cloneable arayüzü ve
    Object.clone() metodu üzerinden ele alıyoruz.

    Diğer örnekle (copy constructor tabanlı) karşılaştırıldığında dikkat edilmesi
    gereken noktaları şöyle özetleyebiliriz.

      1) Cloneable, hiçbir metot içermeyen bir "marker interface"tir. Asıl clone() metodu
         Object sınıfından gelir ve varsayılan olarak shallow copy işlemi yapar.
      2) Object.clone() checked bir exception (CloneNotSupportedException) fırlatır. Tedbir almak
         gerekir yani try/catch. Bu da clone() metodunu her yerde try/catch ile kirletir.
      3) Mutable alanları (burada sections alanı olarak değerlendirdik) manuel olarak derin kopyalamayı unutursak,
         klonlar orijinal nesneyle aynı listeyi paylaşır ve bu hata derleme zamanında değil,
         çalışma zamanında (ve genellikle çok sonra, başka bir yerde) fark edilir.

    Bu nedenlerden genelde modern Java'da Cloneable implementasyonu yerine
    copy constructor kullanımı tercih edilir.
*/

public class Main {
    public static void main(String[] args) {

        // Prototype yalnızca bir kez, maliyetli constructor üzerinden oluşturulur.
        Report basePrototype = new Report("Base");

        Report salesReport = basePrototype.clone();
        salesReport.setTitle("Sales Report");
        salesReport.setDepartment("Sales");
        salesReport.addSection("Sales Summary");
        salesReport.addSection("Sales Charts");

        Report financeReport = basePrototype.clone();
        financeReport.setTitle("Finance Report");
        financeReport.setDepartment("Finance");
        financeReport.addSection("Finance Summary");
        financeReport.addSection("Finance Charts");
        financeReport.addSection("Finance Notes");

        // sections alanı Report.clone() içinde doğru şekilde derin kopyalandıysa,
        // basePrototype'ın kendi section listesi (Title, Summary, Charts, Notes)
        // hiçbir klondan etkilenmemelidir.
        System.out.println("Base Prototype: " + basePrototype);
        System.out.println("Sales Report: " + salesReport);
        System.out.println("Finance Report: " + financeReport);
    }
}
