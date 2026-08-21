package Behavioral.Observer;

/*
    Subject arayüzü. Yine bir sözleşme olarak tanımlıyoruz.
    Gözlemlenen nesneler bu arayüzü uygulayarak, gözlemcileri kaydedebilir, kaldırabilir ve bilgilendirebilir.
*/
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
