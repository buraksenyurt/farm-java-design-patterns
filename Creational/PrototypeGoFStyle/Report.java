package Creational.PrototypeGoFStyle;

import java.util.ArrayList;
import java.util.List;

/*
    GoF (Gang of Four) tarzı Prototype implementasyonu.

    Report sınıfı Cloneable arayüzünü uygular ve Object.clone() metodunu override eder.

    Object.clone() varsayılan olarak shallow copy(sığ kopyalama) yapar.
    title, department, style gibi String alanlar immutable olduğu için sorun çıkarmaz;
    ama sections (List<String>, referans tipi) super.clone() ile olduğu gibi kopyalanır.
    Yani clone() içindeki manuel deep-copy satırını kaldırıp sadece
    "return (Report) super.clone();" ile bırakırsak, bir klon üzerinde addSection()
    çağırmak orijinal prototype nesnesi ve diğer klonları da sessizce değiştirir zira hepsi
    aynı ArrayList referansını paylaşmaktadır. Bunu görmek için aşağıdaki
    
    "cloned.sections = new ArrayList<>(this.sections);" 
    
    satırını geçici olarak yorum satırına alıp Main'i tekrar çalıştırabiliriz.
*/
public class Report implements Cloneable {
    private String title;
    private String department;
    private String style;
    private List<String> sections = new ArrayList<>();

    /*
     * Maliyeti yüksek olan yapıcı metod (constructor).
     * Creational/Prototype paketindeki örnekle aynı senaryo: varsayılan bölümler ve
     * stil profili burada "pahalı" şekilde kuruluyor, klonlama ile bu maliyet
     * tekrar ödenmeyecek.
     */
    public Report(String department) {
        this.department = department;
        this.style = "Default Style";
        this.sections = new ArrayList<>(List.of("Title", "Summary", "Charts", "Notes"));

        System.out.println("Report nesnesi " + this.department + " için oluşturuluyor. (Maliyet yüksek)");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addSection(String section) {
        this.sections.add(section);
    }

    @Override
    public Report clone() {
        try {
            Report cloned = (Report) super.clone();
            cloned.sections = new ArrayList<>(this.sections);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloneable uygulanmasına rağmen clone başarısız oldu", e);
        }
    }

    @Override
    public String toString() {
        return "Report{title='" + title + "', department='" + department
                + "', style='" + style + "', sections=" + sections + "}";
    }
}
