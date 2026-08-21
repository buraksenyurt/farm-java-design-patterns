package Behavioral.Observer;

// Observer arayüzü. Gözlemciler bu arayüzü uygulayarak, gözlemlenen nesnelerden gelen güncellemeleri alabilirler.
public interface Observer {
    void update(String symbol, double price);
}
