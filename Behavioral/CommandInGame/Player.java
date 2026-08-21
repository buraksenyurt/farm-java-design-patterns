package Behavioral.CommandInGame;

// Receiver: Oyuncunun hareket ve atak işlemlerini gerçekleştiren sınıf
public class Player {
    public void move(Vector direction) {
        System.out.println("Oyuncu hareket ediyor: " + direction);

    }

    public void attack() {
        System.out.println("Oyuncu atak ediyor!");
    }
}
