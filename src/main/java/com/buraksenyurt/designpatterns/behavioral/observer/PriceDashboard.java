package com.buraksenyurt.designpatterns.behavioral.observer;

public class PriceDashboard implements Observer {
    @Override
    public void update(String symbol, double price) {
        System.out.println("PriceDashboard: " + symbol + " fiyatı güncellendi: " + price);
    }
    
}
