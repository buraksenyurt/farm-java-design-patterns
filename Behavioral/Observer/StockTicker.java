package Behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

/*
    StockTicker sınıfı, Observer arayüzünü uygulayan gözlemcilerin bir listesini tutar.
    Dolayısıyla bildirimler için kayıt olan(register) asıl gözlemcileri bilgilendirebilir.

    setPrice() metodu çağrıldığında, gözlemciler notifyObservers() metodu ile bilgilendirilir.
    Dikkat edileceği üzere bu sınıf asıl iş yapan somut bileşenleri(Observer implementasyonlarını) bilmez.
*/
public class StockTicker implements Subject {
    private String symbol;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public void setPrice(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }
}
