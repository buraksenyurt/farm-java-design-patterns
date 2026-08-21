package Creational.Prototype;

import java.util.ArrayList;
import java.util.List;

// Prototype sınıfı
public class Report {
    private String title;
    private String department;
    private String style;
    private List<String> sections = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addSection(String section) {
        this.sections.add(section);
    }

    /*
     * Maliyeti yüksek olan yapıcı metodumuz (constructor).
     * 
     * Gerçek bir sistemde burada; varsayılan bölüm şablonlarının, stil profilinin
     * ve belki de bir veritabanı/servis çağrısıyla gelen kurumsal ayarların
     * yüklendiğini hayal edebiliriz. Prototype Design Pattern'in çözmeye çalıştığı
     * asıl problem de budur zaten; maliyetli kurulumun her yeni rapor için tekrar
     * tekrar çalıştırılmak zorunda kalmaması.
     *
     * "new ArrayList<>(List.of(...))" kullanıyoruz; sadece "List.of(...)"
     * kullanırsak elde ettiğimiz liste immutable (değiştirilemez) olur.
     * addSection() metodu bu listeye eleman eklemeye çalıştığında
     * UnsupportedOperationException fırlatılır.
     * 
     * Yani sınıfın kendi kuralı (sections değiştirilebilir olmalı) her iki
     * yapıcı metot için de tutarlı şekilde sağlanmalı, sadece copy constructor'da
     * değil.
     */
    public Report(String department) {
        this.department = department;
        this.style = "Default Style";
        this.sections = new ArrayList<>(List.of("Title", "Summary", "Charts", "Notes"));

        System.out.println("Report nesnesi " + this.department + " için oluşturuluyor. (Maliyet yüksek)");
    }

    /*
     * Copy constructor.
     * 
     * Dikkat edileceği üzere parametre olarak kendisiyle aynı tipten (Report) bir
     * nesneyi referans olarak alıyor ve alanlarını bu kaynaktan kopyalıyor.
     * Yukarıdaki maliyetli constructor'ın aksine burada ne varsayılan bölümler
     * yeniden hesaplanıyor ne de "maliyet yüksek" mesajı basılıyor; sadece mevcut
     * bir nesnenin durumu taşınıyor.
     *
     * sections alanını aktarırken doğrudan atama (this.sections = source.sections)
     * yapmadık. Çünkü List<String> bir referans tipidir; doğrudan atama shallow
     * copy(sığ kopyalama) anlamına gelir ve iki Report nesnesi aynı listeyi
     * paylaşır hale gelir. Böyle bir durumda bir klon üzerinde addSection()
     * çağırmak, orijinal prototype'ı veya diğer klonları da sessizce değiştirirdi.
     * new ArrayList<>(...) ile deep copy (derin kopyalama) yaparak her Report'un
     * kendi bağımsız listesine sahip olmasını garanti ediyoruz.
     */
    public Report(Report source) {
        this.department = source.department;
        this.title = source.title;
        this.style = source.style;
        this.sections = new ArrayList<>(source.sections);
    }

    /*
     * İstemci (client) bu metodu kullanarak "new" çağrısı ile sıfırdan bir rapor
     * nesnesi üretmek yerine, mevcut (ve zaten maliyeti ödenmiş) bir nesnenin
     * kopyasını ister. Prototype Design Pattern gereği nesne kendi kendini nasıl
     * kopyalayacağını bilir, istemcinin bunu bilmesine gerek yoktur.
     */
    public Report copy() {
        return new Report(this);
    }

    @Override
    public String toString() {
        return "Report{title='" + title + "', department='" + department
                + "', style='" + style + "', sections=" + sections + "}";
    }
}
