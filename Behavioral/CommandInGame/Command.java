package Behavioral.CommandInGame;

// Command arayüzü: Eylemi gerçekleştirecek Player sınıfının kullanacağı ortak arayüz
// Böylece aynı komut sadece Player sınıfına değil, farklı oyuncu türlerine de uygulanabilir hale gelir
public interface Command {
    public void execute(Player actor);
}
