package Behavioral.CommandInGame;

// Client: Oyun döngüsü ve oyuncu girişlerini simüle eden sınıf
public class Game {
    public static void main(String[] args) {
        Player yourHero = new Player();
        InputHandler inputHandler = new InputHandler();

        String[] frameInputs = { "W", "S", "S", "A", "S", "D", "SPACE", "SPACE", "W", "ZIP" };

        for (String input : frameInputs) {
            Command command = inputHandler.handleInput(input);
            if (command != null) {
                command.execute(yourHero);
            } else {
                System.out.println("Geçersiz giriş: " + input);
            }
        }

    }
}
