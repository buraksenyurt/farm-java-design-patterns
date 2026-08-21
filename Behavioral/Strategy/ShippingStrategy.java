package Behavioral.Strategy;

// Strateji arayüzü, farklı gönderim stratejilerini temsil eder.
// Bu senaryoda anahtar nokta siparişin farklı gönderim stratejilerine göre hesaplanmasının 
// ortak bir davranış olarak belirlenmesidir.
public interface ShippingStrategy {
    double calculateShipping(Order order);
}
