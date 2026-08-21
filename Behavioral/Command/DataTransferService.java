package Behavioral.Command;

// Receiver rolünü üstlenen sınıf
public class DataTransferService {
    public void addSource(String source, String domain) {
        System.out.println(domain + " domainine kaynak eklendi: " + source);
    }

    public void removeSource(String source, String domain) {
        System.out.println(domain + " domaininden kaynak kaldırıldı: " + source);
    }

    public void addQuery(String query, String domain) {
        System.out.println(domain + " domainine sorgu eklendi: " + query);
    }

    // public void removeQuery(String query, String domain) {
    //     System.out.println(domain + " domaininden sorgu kaldırıldı: " + query);
    // }

    public void addDestination(String destination, String domain) {
        System.out.println(domain + " domainine hedef eklendi: " + destination);
    }

    // public void removeDestination(String destination, String domain) {
    //     System.out.println(domain + " domaininden hedef kaldırıldı: " + destination);
    // }
}
